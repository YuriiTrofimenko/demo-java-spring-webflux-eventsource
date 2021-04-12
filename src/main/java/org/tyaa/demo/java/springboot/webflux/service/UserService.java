package org.tyaa.demo.java.springboot.webflux.service;

import org.tyaa.demo.java.springboot.webflux.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

	void createUser(User user);

	Flux<User> findAll();

	Mono<User> update(User user);

	Mono<Void> delete(Integer id);

	Mono<User> findOne(Integer id);

	Flux<User> findByUserName(String userName);

}
