package com.truebadge.models;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document - applied at the class level to indicate this class is a candidate for mapping to the database. 
//You can specify the name of the collection where the database will be stored.
@Document(collection = "audios")
public class Audio {
	@Id
	private ObjectId _id;

	private String title;
	private Binary audio;

	public Audio() {
	}

	public Audio(ObjectId id, String title, Binary audio) {
		super();
		this._id = id;
		this.title = title;
		this.audio = audio;
	}

	public String get_id() {
		return _id.toHexString();
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Binary getAudio() {
		return audio;
	}

	public void setAudio(Binary audio) {
		this.audio = audio;
	}

}
