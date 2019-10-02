package com.truebadge.util;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.web.multipart.MultipartFile;

import com.truebadge.models.Audio;
import com.truebadge.models.Photo;
import com.truebadge.repositories.AudioRepository;
import com.truebadge.repositories.PhotoRepository;

public class MediaFileUtil {
	
	public static String uploadPhoto(PhotoRepository photoRepo, String title, MultipartFile multipart) {
		try {
			Photo photo = new Photo();
			photo.setTitle(title);
			photo.setImage(new Binary(BsonBinarySubType.BINARY, multipart.getBytes()));
			System.out.println("Blabla audio"+ photo.toString()+ " " + title);

			photoRepo.save(photo);
			return photo.get_id();
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
	
	public static String uploadAudio(AudioRepository audioRepo, String title, MultipartFile multipart) {
		try {
			Audio audio = new Audio();
			audio.setTitle(title);
			audio.setAudio(new Binary(BsonBinarySubType.BINARY, multipart.getBytes()));
			System.out.println("Blabla audio"+ audio.toString() + " " +title);
			audioRepo.save(audio);
			return audio.get_id();
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
	
	

}
