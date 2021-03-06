package com.truebadge.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.truebadge.interfaces.MediaFileControllerInterface;
import com.truebadge.models.MediaFileImpl;
import com.truebadge.models.Photo;
import com.truebadge.repositories.PhotoRepository;
import com.truebadge.util.JSONConverterUtil;
import com.truebadge.util.MediaFileUtil;

@RestController
@RequestMapping("/photos")
public class PhotoController implements MediaFileControllerInterface {
	@Autowired
	private PhotoRepository repository;
	
	private String LOCAL_DESKTOP = "/Users/michielu/Desktop/";
	
	// Upload Photo
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public JSONObject singleFileUpload(MultipartFile multipart, @RequestParam("title") String title) {
		 JSONObject photoIdJSON= MediaFileUtil.uploadPhoto(repository, title, multipart);
		return photoIdJSON;
	}

	@RequestMapping(value="/retrieve" ,method = RequestMethod.POST)
	public JSONObject retrieveFile(@RequestBody JSONObject id){
	    MediaFileImpl demoPhoto = MediaFileUtil.retrievePhoto(repository, (String)id.get("id"));
	    Binary document = ((Photo)demoPhoto).getImage();
	    String title = demoPhoto.getTitle();
	    try {
	    	 if(document != null) {
	    		 //Testing by printing out to desktop
	 	        FileOutputStream fileOuputStream = null;
	 	        try {
	 	            fileOuputStream = new FileOutputStream(LOCAL_DESKTOP +title+".jpg"); // store extention? -- not needed bc I'll never download it like this
	 	            fileOuputStream.write(document.getData());
	 	        } catch (Exception e) {
	 	            e.printStackTrace();
	 	            
	 	            return JSONConverterUtil.convertToJSON(false, "failure - filtoutputstream writing error");
	 	        } finally {
	 	            if (fileOuputStream != null) {
	 	                try {
	 	                    fileOuputStream.close();
	 	                } catch (IOException e) {
	 	                    e.printStackTrace();
	 		 	            return JSONConverterUtil.convertToJSON(false, "failure - could not close filestream");
	 	                }
	 	            }
	 	        }
	 	    }
	            return JSONConverterUtil.convertToJSON(true, "success - image retrieved");

	    } catch(Exception e) {
	    	e.printStackTrace();
	    	return JSONConverterUtil.convertToJSON(false , "failure - could not get photo");
	    }
	}

}