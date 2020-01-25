package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.UserDao;
import com.domain.*;

@RestController
@RequestMapping("/user")
public class UserController {
private UserDao userDao;
@Autowired
public void setUserDao(UserDao dao) {
	System.out.print("initialized dao");
	userDao = dao;
}
@PostMapping(value ="/add")
public ResponseEntity<Integer> add(@RequestBody User user) {
	/*save user*/
	int result = userDao.save(user);
	return new ResponseEntity<Integer>(result,HttpStatus.OK);
}
@RequestMapping("/get/{user_id}")
public ResponseEntity<User> get(@PathVariable Integer user_id) {
	//userDao.save(user);
	User user = userDao.get(user_id);
	return new ResponseEntity<User>(user,HttpStatus.OK);
}
}
