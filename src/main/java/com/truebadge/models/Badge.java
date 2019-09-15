package com.truebadge.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Badge {
	@Id
	public ObjectId _id;
	
	public String name;
	
}
