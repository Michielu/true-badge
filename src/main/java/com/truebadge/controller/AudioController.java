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

import com.truebadge.models.Audio;
import com.truebadge.repositories.AudioRepository;

@RestController
@RequestMapping("/audios")
public class AudioController {
	@Autowired
	private AudioRepository repository;
	
	private String LOCAL_DESKTOP = "/Users/michielu/Desktop/";

	// Upload Audio
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String singleFileUpload(MultipartFile multipart, @RequestParam("title") String title) {
		try {
			Audio demoAudio = new Audio();
			demoAudio.setTitle(title);
			demoAudio.setAudio(new Binary(BsonBinarySubType.BINARY, multipart.getBytes()));
			repository.save(demoAudio);
			System.out.println(demoAudio);
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
		return "success";
	}

	@RequestMapping(value="/retrieve" ,method = RequestMethod.POST)
	public String retrieveFile(@RequestBody JSONObject id){
	    Audio demoAudio = repository.findBy_id(new ObjectId((String)id.get("id")));
	    Binary document = demoAudio.getAudio();
	    String title = demoAudio.getTitle();
	    try {
	    	 if(document != null) {
	    		 //Testing by printing out to desktop
	 	        FileOutputStream fileOuputStream = null;
	 	        try {
	 	            fileOuputStream = new FileOutputStream(LOCAL_DESKTOP +title+".m4a"); // store extention? -- not needed bc I'll never download it like this
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
	 	    return "success - audio retrieved";

	    } catch(Exception e) {
	    	e.printStackTrace();
	    	return "failure - could not get audio";
	    }
	}

}