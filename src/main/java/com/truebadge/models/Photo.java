package com.truebadge.models;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document - applied at the class level to indicate this class is a candidate for mapping to the database. 
//You can specify the name of the collection where the database will be stored.
@Document(collection = "photos")
public class Photo extends MediaFileImpl{

	private Binary image;

	public Photo() {}

	public Binary getImage() {
		return image;
	}

	public void setImage(Binary image) {
		this.image = image;
	}
}