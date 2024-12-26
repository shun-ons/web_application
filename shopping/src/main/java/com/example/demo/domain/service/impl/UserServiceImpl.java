package com.example.demo.domain.service.impl;

import java.util.List;

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
		mapper.insertOne(user);
	}
	
	@Override
	public List<MUser> getUsers() {
		return mapper.findMany();
	}
	
	@Override
	public MUser getUserOne(String mailAddress) {
		return mapper.findOne(mailAddress);
	}
	
	@Override
	public void updateUserOne(String mailAddress,String password,String name) {
		mapper.updateOne(mailAddress, password, name);
	}
	
	@Override
	public void deleteUserOne(String mailAddress) {
		mapper.deleteOne(mailAddress);
	}
}
