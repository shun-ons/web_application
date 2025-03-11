package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MUser;
import com.example.demo.repository.UserMapper;
import com.example.demo.service.UserService;

/**
 * ユーザーサービスの実装クラス
 * @author 夏木翔吾
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
        String maxUserId = mapper.getMaxUserId(); // 最大の userId を取得
        int newId = 1; // デフォルトの userId (最初のユーザーの場合)

        if (maxUserId != null && maxUserId.startsWith("u")) {
            try {
                // "u" を除去して数値部分を取得
                int lastId = Integer.parseInt(maxUserId.substring(1));
                newId = lastId + 1; // 次の userId を計算
            } catch (NumberFormatException e) {
                throw new IllegalStateException("ユーザーIDのフォーマットが不正です: " + maxUserId);
            }
        }

        // u01, u02, ... というフォーマットにする
        String newUserId = String.format("u%02d", newId);
        user.setUserId(newUserId);
        
        user.setRole("ROLE_GENERAL");

        // パスワードを暗号化
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        user.setPoint(5000);

        // ユーザーをデータベースに追加
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
