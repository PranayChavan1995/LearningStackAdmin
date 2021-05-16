package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.UserDao;
import com.example.model.User;
import com.example.service.UserService;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao dao;

	public User getUserByName(String userName) {
		return dao.getUserByName(userName);
	}

}
