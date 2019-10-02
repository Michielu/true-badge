package com.truebadge.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.truebadge.models.Audio;
import com.truebadge.models.Photo;

@Repository
public interface AudioRepository  extends MongoRepository<Audio, String> {
	public Photo findBy_id(ObjectId _id);
}