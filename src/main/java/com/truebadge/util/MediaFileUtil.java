package com.truebadge.util;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.truebadge.models.Photo;
import com.truebadge.repositories.PhotoRepository;

public class MediaFileUtil {
	
	public static String uploadPhoto( PhotoRepository photoRepo, String title, MultipartFile multipart) {
		try {
			Photo demoPhoto = new Photo();
			demoPhoto.setTitle(title);
			demoPhoto.setImage(new Binary(BsonBinarySubType.BINARY, multipart.getBytes()));
			photoRepo.save(demoPhoto);
			System.out.println(demoPhoto.get_id());
			return demoPhoto.get_id();
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}

}
