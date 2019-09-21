package com.truebadge.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.validation.Valid;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.truebadge.models.Photo;
import com.truebadge.repositories.PhotoRepository;

@RestController
@RequestMapping("/photos")
public class PhotoController {
	@Autowired
	private PhotoRepository repository;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Photo addPhoto(@Valid @RequestBody Photo photo) {
		photo.set_id(ObjectId.get());
		repository.save(photo);
		return photo;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Photo getPhotoById(@PathVariable("id") ObjectId id) {
		return repository.findBy_id(id);
	}

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
	    Photo demoPhoto = repository.findBy_id(new ObjectId((String)id.get("id")));
	    Binary document = demoPhoto.getImage();
	    String title = demoPhoto.getTitle();
	    try {
	    	 if(document != null) {
	    		 //Testing by printing out to desktop
	 	        FileOutputStream fileOuputStream = null;
	 	        try {
	 	            fileOuputStream = new FileOutputStream("/Users/michielu/Desktop/"+title+".jpg"); // store extention? 
	 	            fileOuputStream.write(document.getData());
	 	        } catch (Exception e) {
	 	            e.printStackTrace();
	 	            return "failure";
	 	        } finally {
	 	            if (fileOuputStream != null) {
	 	                try {
	 	                    fileOuputStream.close();
	 	                } catch (IOException e) {
	 	                    e.printStackTrace();
	 	                    return "failure";
	 	                }
	 	            }
	 	        }
	 	    }
	 	    return "success";

	    } catch(Exception e) {
	    	e.printStackTrace();
	    	return "failure";
	    }
	}

}