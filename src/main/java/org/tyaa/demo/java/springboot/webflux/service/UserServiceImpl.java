package org.tyaa.demo.java.springboot.webflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyaa.demo.java.springboot.webflux.model.User;
import org.tyaa.demo.java.springboot.webflux.repository.UserRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo userRepo;

	@Override
	public void createUser(User user) {
		userRepo.saveAll(Flux.just(user)).subscribe();
	}
	
	@Override
	public Mono<User> findOne(Integer id) {
		return userRepo.findById(id);
	}
	
	@Override
	public Flux<User> findAll() {
		return userRepo.findAll();
	}
	
	@Override
	public Mono<User> update(User user) {
		return userRepo.save(user);
	}
	
	@Override
	public Mono<Void> delete(Integer id) {
		return userRepo.deleteById(id);
	}
	
	@Override
	public Flux<User> findByUserName(String userName) {
		return userRepo.findByUserName(userName);
	}

}
