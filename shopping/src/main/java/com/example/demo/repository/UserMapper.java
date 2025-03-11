package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.MUser;
/**
 * muserテーブルを管理するクラス.
 * @author 夏木翔吾
 */
@Mapper
public interface UserMapper {

	public int insertOne(MUser user);

	public List<MUser>findMany();
	
	/**ユーザ取得*/
	public MUser findOne(String mailAddress);
	
	//ユーザ更新
	public void updateOne(@Param("mailAddress") String mailAddress,@Param("name") String name);
	
	//ユーザ削除
	public void deleteOne(@Param("mailAddress") String mailAddress);
	
	public MUser findByMailAddress(String mailAddress);
	
	public String getMaxUserId();

	public void addPoint(@Param("userId") String userId, @Param("point")int point);

	/**
     * ユーザーのポイントを取得する
     * @param userId ユーザーID
     * @return ポイント
     */
    @Select("SELECT point FROM m_user WHERE userId = #{userId}")
    int findPointByUserId(@Param("userId") String userId);

    /**
     * ユーザーのポイントを更新する
     * @param userId ユーザーID
     * @param point 新しいポイント
     */
    @Update("UPDATE m_user SET point = #{point} WHERE userId = #{userId}")
    void updateUserPoint(@Param("userId") String userId, @Param("point") int point);
    
    /**
     * userIdからuserNameを取得するメソッド.
     * @param userId 取得したいユーザのID.
     * @return ユーザ名.
     */
 	@Select({"SELECT name FROM m_user", "WHERE userId = #{userId}"})
 	public String getUserName(String userId);

}
