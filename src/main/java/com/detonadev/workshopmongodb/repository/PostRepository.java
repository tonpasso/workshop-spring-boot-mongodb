package com.detonadev.workshopmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.detonadev.workshopmongodb.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {

}
