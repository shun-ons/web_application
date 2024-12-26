package com.example.demo.domain.service;

import java.util.List;

import com.example.demo.domain.model.MUser;

public interface UserService {

	/**ユーザ登録*/
	public void signup(MUser user);
	
	//ユーザ情報取得
	public List<MUser>getUsers();
	
	//ユーザ1件取得
	public MUser getUserOne(String mailAdress);
	
	//ユーザ1件更新
	public void updateUserOne(String mailAddress,String password,String name);
	
	public void deleteUserOne(String mailAddress);
}