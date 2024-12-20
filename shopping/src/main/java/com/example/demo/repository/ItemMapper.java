package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.Item;

@Mapper
public interface ItemMapper {
	// 全聚徳
	@Select("SELECT * FROM item")
	public List<Item> selectAll();
	
	// 1件取得
	@Select({"SELECT * FROM item", "WHERE itemId = #{itemId}"})
	public Item selectById(String itemId);
	
	// 登録
	@Insert({"INSERT INTO item(itemId, itemName, itemPrice, ornerName, ornerEmailAddress, salesDateTime)",
			"VALUES(#{itemId}, #{itemName}, #{itemPrice}, #{ornerName}, #{ornerEmailAddress}, #{salesDateTime})"})
	public int insert(Item item);
	
	// 更新
	@Update({"UPDATE ITEM",
		"SET itemName= #{itemName}, itemPrice = #{itemPrice}, ornerName = #{ornerName}, ornerEmailAddress = {ornerEmailAddress}, salesDateTime = #{salesDateTime}",
		"WHERE itemId = #{itemId"})
	public int update(Item item);
	
	// 削除
	@Delete({"DELETE FROM item", "WHERE itemId = #{itemId}"})
	public int delete(String itemId);
}
