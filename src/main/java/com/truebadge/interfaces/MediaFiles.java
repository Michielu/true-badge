package com.truebadge.interfaces;

import org.bson.types.ObjectId;

public interface MediaFiles {
	public String get_id();
	public void set_id(ObjectId _id);
	public String getTitle();
	public void setTitle(String title);
}
