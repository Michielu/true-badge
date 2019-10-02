package com.truebadge.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.truebadge.models.Audio;
import com.truebadge.models.Badge;


public interface BadgeRepository extends MongoRepository<Badge, String> {
	public Badge findBy_id(ObjectId _id);
}
