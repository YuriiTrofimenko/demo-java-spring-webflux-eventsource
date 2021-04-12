package org.tyaa.demo.java.springboot.webflux.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tyaa.demo.java.springboot.webflux.model.User;
import org.tyaa.demo.java.springboot.webflux.service.UserServiceImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import javax.validation.Valid;
import java.time.Duration;

/**
 * @author ankidaemon
 *
 */
@RestController
@RequestMapping("/api/users")
public class HomeController {

	@Autowired
	private UserServiceImpl service;

	@RequestMapping(value = { "/create", "/" }, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void create(@RequestBody @Valid User user) {
		service.createUser(user);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public Mono<User> update(@RequestBody @Valid User user) {
		return service.update(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Mono<User>> findOne(@PathVariable("id") Integer userId) {
		Mono<User> user = service.findOne(userId);
		HttpStatus status = user != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Mono<User>>(user, status);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@ResponseBody
	public Flux<String> findAll() {
		return service.findAll()
				.map(user -> user.toString())
				.delayElements(Duration.ofSeconds(4));
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Integer id) {
		service.delete(id).subscribe();
	}
	
	@RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
	@ResponseBody
	public Flux<User> findByUserName(@PathVariable("username") String userName) {
		return service.findByUserName(userName);
	}

}
