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

        if (maxUserId != null && maxUserId.startsWith("u")) {
            // "u"を除いた部分を数値に変換し、+1する
            newId = Integer.parseInt(maxUserId.substring(1)) + 1;
        }

        // 新しいuserIdを生成
        String newUserId = String.format("u%02d", newId);
        user.setUserId(newUserId);

        user.setRole("ROLE_GENERAL");

        // パスワード暗号化
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        user.setPoint(1000);

        mapper.insertOne(user);
    }

    /**
     * 全ユーザーのリストを取得する
     * 
     * @return ユーザーリスト
     */
    @Override
    public List<MUser> getUsers() {
        return mapper.findMany();
    }

    /**
     * メールアドレスを基にユーザー情報を取得する
     * 
     * @param mailAddress 取得するユーザーのメールアドレス
     * @return 該当ユーザー情報
     */
    @Override
    public MUser getUserOne(String mailAddress) {
        return mapper.findOne(mailAddress);
    }

    /**
     * ユーザー情報を更新する
     * 
     * @param mailAddress 更新対象のメールアドレス
     * @param name        更新するユーザー名
     */
    @Override
    public void updateUserOne(String mailAddress, String name) {
        mapper.updateOne(mailAddress, name);
    }

    /**
     * 指定したメールアドレスのユーザーを削除する
     * 
     * @param mailAddress 削除対象のメールアドレス
     */
    @Override
    public void deleteUserOne(String mailAddress) {
        mapper.deleteOne(mailAddress);
    }

    /**
     * メールアドレスでユーザー情報を取得する
     * 
     * @param mailAddress 取得対象のメールアドレス
     * @return 該当ユーザー情報
     */
    @Override
    public MUser getUserByMailAddress(String mailAddress) {
        return mapper.findByMailAddress(mailAddress);
    }

    /**
     * ユーザーIDからユーザー名を取得する
     * 
     * @param userId 取得対象のユーザーID
     * @return ユーザー名
     */
    public String getUserName(String userId) {
        return mapper.getUserName(userId);
    }

    /**
     * 指定したユーザーのポイントを追加する
     * 
     * @param userId ユーザーID
     * @param point  追加するポイント
     */
    @Override
    public void addPoint(String userId, int point) {
        mapper.addPoint(userId, point);
    }
}
