package com.truebadge.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import com.truebadge.interfaces.MediaFiles;

//Should I even have Photo and Audio classes? 
public class MediaFileImpl implements MediaFiles {
	//@Id applied at the field level to mark the field used for identiy purpose
	@Id
	private ObjectId _id;

	//This is for testing
	private String title;
	
	public MediaFileImpl() {
		
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
}
