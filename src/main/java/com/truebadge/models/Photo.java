package com.truebadge.models;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//@Document - applied at the class level to indicate this class is a candidate for mapping to the database. 
//You can specify the name of the collection where the database will be stored.
@Document(collection = "photos")
public class Photo {
	//@Id applied at the field level to mark the field used for identiy purpose
	@Id
	private ObjectId _id;

	//This is for testing
	private String title;
	private Binary image;

	public Photo() {
	}

	public Photo(ObjectId id, String title, Binary image) {
		super();
		this._id = id;
		this.title = title;
		this.image = image;
	}

	//Do I need these? 
	// ObjectId needs to be converted to string
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

	public Binary getImage() {
		return image;
	}

	public void setImage(Binary image) {
		this.image = image;
	}

}
