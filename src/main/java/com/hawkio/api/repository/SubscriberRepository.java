package com.hawkio.api.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.hawkio.api.model.Subscriber;

public interface SubscriberRepository extends MongoRepository<Subscriber,ObjectId> {
	Subscriber findByEmailId(String email);
}
