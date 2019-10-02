package com.truebadge.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document - applied at the class level to indicate this class is a candidate for mapping to the database. 
//You can specify the name of the collection where the database will be stored.
@Document(collection = "badge")
public class Badge {
	@Id
	private ObjectId _id;
	
	private String name;
	
	private String photoId;
	private String audioId;
	
	public Badge() {}
	
	public String get_id() {
		return _id.toHexString();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public String getAudioId() {
		return audioId;
	}

	public void setAudioId(String audioId) {
		this.audioId = audioId;
	}
}
