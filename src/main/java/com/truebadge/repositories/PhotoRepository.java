package com.truebadge.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.truebadge.models.Photo;

public interface PhotoRepository extends MongoRepository<Photo, String> {
	public Photo findBy_id(ObjectId _id);
}
