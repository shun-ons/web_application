package com.example.demo.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private UserService service;
	
	@Autowired
    public UserDetailsServiceImpl(@Lazy UserService service){
        this.service = service;
    }
	
	@Override
	public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException{
		
		//ユーザ情報取得
		MUser loginUser = service.getUserByMailAddress(mailAddress);
		
		//ユーザが存在しない場合
		if(loginUser == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
	   //権限リスト
		GrantedAuthority authority = new SimpleGrantedAuthority(loginUser.getRole());
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);
		
		
		//UserDetails作成
		UserDetails userDetails = (UserDetails)new User(loginUser.getMailAddress(),loginUser.getPassword(),authorities);
		
		return userDetails;
		
		
	}

}
