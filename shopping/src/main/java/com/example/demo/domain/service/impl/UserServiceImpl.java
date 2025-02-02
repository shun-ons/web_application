package com.example.demo.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;
import com.example.demo.repository.UserMapper;

/**
 * ユーザーサービスの実装クラス
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;
    
    @Autowired
    private PasswordEncoder encoder;

    /**
     * ユーザー登録処理を行う
     * 
     * @param user 登録するユーザー情報
     */
    @Override
    public void signup(MUser user) {
        String maxUserId = mapper.getMaxUserId();
        int newId = 1;

	    // 新しいuserIdを生成
	    String newUserId = String.format("u%02d", newId);
	    user.setUserId(newUserId);
	    
		user.setRole("ROLE_GENERAL");
		
		//パスワード暗号化
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));
		user.setPoint(5000);
		
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
	public void updateUserOne(String mailAddress,String name) {
		//String encryptPassword = encoder.encode(password);
		mapper.updateOne(mailAddress,name);
	}
	
	@Override
	public void deleteUserOne(String mailAddress) {
		mapper.deleteOne(mailAddress);
	}
	
	@Override
	public MUser getUserByMailAddress(String mailAddress) {
		return mapper.findByMailAddress(mailAddress);
	}
	
	// userIdからuserNameを取得.
	public String getUserName(String userId) {
		return mapper.getUserName(userId);
	}
	@Override
	 public void addPoint(String userId, int point) {
        mapper.addPoint(userId, point);
    }
}
