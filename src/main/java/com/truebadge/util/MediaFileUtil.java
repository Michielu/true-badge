package com.truebadge.util;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.truebadge.models.Audio;
import com.truebadge.models.MediaFileImpl;
import com.truebadge.models.Photo;
import com.truebadge.repositories.AudioRepository;
import com.truebadge.repositories.PhotoRepository;

public class MediaFileUtil {
	
	public static JSONObject uploadPhoto(PhotoRepository photoRepo, String title, MultipartFile multipart) {
		try {
			Photo photo = new Photo();
			photo.setTitle(title);
			photo.setImage(new Binary(BsonBinarySubType.BINARY, multipart.getBytes()));
			photoRepo.save(photo);
			
			return JSONConverterUtil.convertToJSON(true, "", new String[] {"photoId"}, new String[] {photo.get_id()});
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return JSONConverterUtil.convertToJSON(false, e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return JSONConverterUtil.convertToJSON(false, e.getMessage());
		}
	}
	
	public static JSONObject uploadAudio(AudioRepository audioRepo, String title, MultipartFile multipart) {
		try {
			Audio audio = new Audio();
			audio.setTitle(title);
			audio.setAudio(new Binary(BsonBinarySubType.BINARY, multipart.getBytes()));
			audioRepo.save(audio);
			return JSONConverterUtil.convertToJSON(true, "", new String[] {"audioId"}, new String[] {audio.get_id()});
		} catch (IllegalArgumentException e) {
			return JSONConverterUtil.convertToJSON(false, e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JSONConverterUtil.convertToJSON(false, e.getMessage());
		}
	}
	
	public static Photo retrievePhoto(PhotoRepository photoRepo, String id) {
		return photoRepo.findBy_id(new ObjectId(id));
	}
	
	public static Audio retrieveAudio(AudioRepository audioRepo, String id) {
		return audioRepo.findBy_id(new ObjectId(id));
	}

}
