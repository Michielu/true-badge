package com.truebadge.models;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document - applied at the class level to indicate this class is a candidate for mapping to the database. 
//You can specify the name of the collection where the database will be stored.
@Document(collection = "audios")
public class Audio extends MediaFileImpl{
	private Binary audio;

	public Audio() {}

	public Binary getAudio() {
		return audio;
	}

	public void setAudio(Binary audio) {
		this.audio = audio;
	}
}