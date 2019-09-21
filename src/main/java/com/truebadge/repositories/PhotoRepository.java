package com.truebadge.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.truebadge.models.Photo;

public interface PhotoRepository extends MongoRepository<Photo, String> {
//	public String addPhoto();
//	public Photo getPhotoById();
	public Photo findBy_id(ObjectId _id);

}
