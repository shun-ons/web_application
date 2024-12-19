package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.model.MUser;

@Mapper
public interface UserMapper {

	public int insertOne(MUser user);
}
