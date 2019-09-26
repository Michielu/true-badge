package com.truebadge.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.truebadge.models.Audio;

public interface AudioRepository extends MongoRepository<Audio, String> {
	public Audio findBy_id(ObjectId _id);
}
