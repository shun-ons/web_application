package com.example.demo.domain.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;
import com.example.demo.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper mapper;
	
	@Override
	public void signup(MUser user) {
		//user.setFacultyId(1);
		//user.setFacultyName("システム工学部");
		user.setUserId(UUID.randomUUID().toString());
		mapper.insertOne(user);
	}
	
	@Override
	public List<MUser> getUsers() {
		return mapper.findMany();
	}
	
	@Override
	public MUser getUserByMailAddress(String mailAddress) {
		return mapper.findByMailAddress(mailAddress);
	}
}
