package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.domain.model.MUser;

@Mapper
public interface UserMapper {

	public int insertOne(MUser user);

	public List<MUser>findMany();
	
	/**ユーザ取得*/
	public MUser findOne(String mailAddress);
	
	//ユーザ更新
	public void updateOne(@Param("mailAddress") String mailAddress,@Param("password") String password,@Param("name") String name);
	
	//ユーザ削除
	public void deleteOne(@Param("mailAddress") String mailAddress);
	
	public MUser findByMailAddress(String mailAddress);
	
	public String getMaxUserId();

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
}
