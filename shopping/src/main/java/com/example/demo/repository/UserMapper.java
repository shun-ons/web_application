package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.model.MUser;

@Mapper
public interface UserMapper {

	public int insertOne(MUser user);

	public List<MUser>findMany();
	
	/**ユーザ取得*/
	public MUser findOne(String mailAddress);
	
	//ユーザ更新
	public void updateOne(@Param("mailAddress") String mailAddress,@Param("password") String password,@Param("name") String name);
	
	public void deleteOne(@Param("mailAddress") String mailAddress);
}
