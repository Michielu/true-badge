package com.truebadge.controller;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.truebadge.models.Badge;
import com.truebadge.repositories.AudioRepository;
import com.truebadge.repositories.BadgeRepository;
import com.truebadge.repositories.PhotoRepository;
import com.truebadge.util.MediaFileUtil;

@RestController
@RequestMapping("/badge")
public class BadgeController{
	@Autowired
	private BadgeRepository repository;
	@Autowired
	private AudioRepository audioRepo;
	@Autowired
	private PhotoRepository photoRepo;

	
	private String LOCAL_DESKTOP = "/Users/michielu/Desktop/";

	// Upload Badge
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String singleFileUpload(MultipartFile audio, MultipartFile photo, String title) {
		try {
			String audioId = MediaFileUtil.uploadAudio(audioRepo, title, audio);
			String photoId = MediaFileUtil.uploadPhoto(photoRepo, title, photo);
			
			Badge badge = new Badge();
			badge.setAudioId(audioId);
			badge.setPhotoId(photoId);
			badge.setName(title);
		
			repository.save(badge);
			System.out.println(badge.get_id());
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
		return "success";
	}

//	@RequestMapping(value="/retrieve" ,method = RequestMethod.POST)
//	public String retrieveFile(@RequestBody JSONObject id){
//	    MediaFileImpl demoAudio = repository.findBy_id(new ObjectId((String)id.get("id")));
//	    Binary document = ((Audio) demoAudio).getAudio();
//	    String title = demoAudio.getTitle();
//	    try {
//	    	 if(document != null) {
//	    		 //Testing by printing out to desktop
//	 	        FileOutputStream fileOuputStream = null;
//	 	        try {
//	 	            fileOuputStream = new FileOutputStream(LOCAL_DESKTOP +title+".m4a"); // store extention? -- not needed bc I'll never download it like this
//	 	            fileOuputStream.write(document.getData());
//	 	        } catch (Exception e) {
//	 	            e.printStackTrace();
//	 	            return "failure - filtoutputstream writing error"; //TODO handle failures better 
//	 	        } finally {
//	 	            if (fileOuputStream != null) {
//	 	                try {
//	 	                    fileOuputStream.close();
//	 	                } catch (IOException e) {
//	 	                    e.printStackTrace();
//	 	                    return "failure - could not close filestream";
//	 	                }
//	 	            }
//	 	        }
//	 	    }
//	 	    return "success - audio retrieved";
//
//	    } catch(Exception e) {
//	    	e.printStackTrace();
//	    	return "failure - could not get audio";
//	    }
//	}

}