package com.truebadge.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.truebadge.models.Photo;

@Repository
public interface PhotoRepository  extends MongoRepository<Photo, String> {
	//The collection items get saved to is the Photo bc of up here ^^ 
	public Photo findBy_id(ObjectId _id);
}