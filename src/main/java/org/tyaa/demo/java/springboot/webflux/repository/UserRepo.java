package org.tyaa.demo.java.springboot.webflux.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.tyaa.demo.java.springboot.webflux.model.User;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepo extends ReactiveMongoRepository<User, Integer> {
	// @Query("{ 'userName': ?0 }")
    Flux<User> findByUserName(final String userName);
}
