package com.truebadge.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import org.bson.BsonBinarySubType;
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

@RestController
@RequestMapping("/photos")
public class PhotoController implements MediaFileControllerInterface {
	@Autowired
	private PhotoRepository repository;
	
	private String LOCAL_DESKTOP = "/Users/michielu/Desktop/";
	
	// Upload Photo
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String singleFileUpload(MultipartFile multipart, @RequestParam("title") String title) {
		try {
			Photo demoPhoto = new Photo();
			demoPhoto.setTitle(title);
			demoPhoto.setImage(new Binary(BsonBinarySubType.BINARY, multipart.getBytes()));
			repository.save(demoPhoto);
			System.out.println(demoPhoto);
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
		return "success";
	}

	@RequestMapping(value="/retrieve" ,method = RequestMethod.POST)
	public String retrieveFile(@RequestBody JSONObject id){
	    MediaFileImpl demoPhoto = repository.findBy_id(new ObjectId((String)id.get("id")));
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
	 	            return "failure - filtoutputstream writing error"; //TODO handle failures better 
	 	        } finally {
	 	            if (fileOuputStream != null) {
	 	                try {
	 	                    fileOuputStream.close();
	 	                } catch (IOException e) {
	 	                    e.printStackTrace();
	 	                    return "failure - could not close filestream";
	 	                }
	 	            }
	 	        }
	 	    }
	 	    return "success - image retrieved";

	    } catch(Exception e) {
	    	e.printStackTrace();
	    	return "failure - could not get photo";
	    }
	}

}