package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.MUser;

/**
 * ユーザーサービスのインターフェース
 * @author 夏木翔吾
 */
public interface UserService {

    /**
     * ユーザー登録を行う
     * 
     * @param user 登録するユーザー情報
     */
    public void signup(MUser user);

    /**
     * すべてのユーザー情報を取得する
     * 
     * @return ユーザーリスト
     */
    public List<MUser> getUsers();

    /**
     * 指定したユーザーの情報を取得する
     * 
     * @param userId 取得対象のユーザーID
     * @return 該当ユーザー情報
     */
    public MUser getUserOne(String userId);

    /**
     * ユーザー情報を更新する
     * 
     * @param mailAddress 更新対象のメールアドレス
     * @param name        更新するユーザー名
     */
    public void updateUserOne(String mailAddress, String name);

    /**
     * 指定したメールアドレスのユーザーを削除する
     * 
     * @param mailAddress 削除対象のメールアドレス
     */
    public void deleteUserOne(String mailAddress);

    /**
     * メールアドレスでユーザー情報を取得する
     * 
     * @param mailAddress 取得対象のメールアドレス
     * @return 該当ユーザー情報
     */
    public MUser getUserByMailAddress(String mailAddress);

    /**
     * 指定したユーザーのポイントを追加する
     * 
     * @param userId ユーザーID
     * @param point  追加するポイント
     */
    public void addPoint(String userId, int point);
}
