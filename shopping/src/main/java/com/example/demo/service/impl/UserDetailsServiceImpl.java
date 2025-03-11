package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MUser;
import com.example.demo.service.UserService;

/**
 * Spring Securityのユーザー認証を行うサービスクラス
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private UserService service;

    /**
     * コンストラクタ
     * 
     * @param service ユーザーサービス
     */
    public UserDetailsServiceImpl(@Lazy UserService service) {
        this.service = service;
    }

    /**
     * 指定されたメールアドレスのユーザー情報をロードする.
     * 
     * @param mailAddress メールアドレス
     * @return ユーザー情報
     * @throws UsernameNotFoundException ユーザーが存在しない場合
     */
    @Override
    public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {
        
        MUser loginUser = service.getUserByMailAddress(mailAddress);

        if (loginUser == null) {
            throw new UsernameNotFoundException("user not found");
        }

        GrantedAuthority authority = new SimpleGrantedAuthority(loginUser.getRole());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);

        return new User(loginUser.getMailAddress(), loginUser.getPassword(), authorities);
    }
}
