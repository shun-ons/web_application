package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Item;

@Mapper
@Repository
public interface ItemMapper {
	// 全聚徳
	@Select("SELECT * FROM item")
	public List<Item> selectAll();
	
	// 1件取得
	@Select({"SELECT * FROM item", "WHERE itemId = #{itemId}"})
	public Item selectById(String itemId);
	
	// ornerIdから商品を取得.
	@Select({"SELECT * FROM item WHERE ornerId = #{ornerId}"})
	public List<Item> selectByOrnerId(String ornerId);
	
	// 登録
	@Insert({"INSERT INTO item(itemId, itemName, itemPrice, ornerName, ornerId, message, salesDateTime, isSold)",
			"VALUES(#{itemId}, #{itemName}, #{itemPrice}, #{ornerName}, #{ornerId}, #{message}, #{salesDateTime}, true)"})
	public int insert(Item item);
	
	// 更新
	@Update({"UPDATE ITEM",
		"SET itemName= #{itemName}, itemPrice = #{itemPrice}, message = #{comment}, salesDateTime = #{salesDateTime}",
		"WHERE itemId = #{itemId}"})
	public int update(Item item);
	
	// 削除
	@Delete({"DELETE FROM item", "WHERE itemId = #{itemId}"})
	public int delete(String itemId);
	
	// userIdからuserNameを取得
	@Select({"SELECT name FROM m_user", "WHERE userId = #{userId}"})
	public String getUserName(String userId);
	
	// isSoldを変更.
	@Update({
	    "UPDATE item",
	    "SET isSold = #{isSold} WHERE itemId = #{itemId}"
	})
	public int updateIsSold(String itemId, boolean isSold);

}
