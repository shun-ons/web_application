package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Item;

/**
 * itemテーブルを操作するためのクラス.
 * @author 大西竣介
 */
@Mapper
@Repository
public interface ItemMapper {
	/**
	 * データベースから全件取得するためのメソッド.
	 * @return Item型のリストが返される.全ての商品が格納されている.
	 */
	@Select("SELECT * FROM item ORDER BY salesDateTime DESC")
	public List<Item> selectAll();
	
	/**
	 * itemIdを用いて商品を取得するメソッド.
	 * @param itemId 取得したい商品のID.
	 * @return Item型のオブジェクト.取得したい商品のデータが含まれている.
	 */
	@Select({"SELECT * FROM item WHERE itemId = #{itemId} ORDER BY salesDateTime DESC"})
	public Item selectById(String itemId);
	
	/**
	 * orderIdを用いて,あるユーザが出品した商品を取得するためのメソッド.
	 * @param ornerId あるユーザのID.これを用いて商品を検索する.
	 * @return Item型のリスト.引数で指定したユーザが出品した商品が含まれている.
	 */
	@Select({"SELECT * FROM item WHERE ornerId = #{ornerId} ORDER BY salesDateTime DESC"})
	public List<Item> selectByOrnerId(String ornerId);
	
	/**
	 * 新しい商品を出品するためのメソッド.データベースに新たな行を追加する.
	 * @param item 出品したい商品.
	 * @return 処理対応件数.
	 */
	@Insert({"INSERT INTO item(itemId, itemName, itemPrice, ornerName, ornerId, message, inCart, isSold,salesDateTime)",
			"VALUES(#{itemId}, #{itemName}, #{itemPrice}, #{ornerName}, #{ornerId}, #{message}, false, true, #{salesDateTime})"})
	public int insert(Item item);
	
	/**
	 * ある商品のデータを全て更新するためのメソッド.
	 * @param item 変更後の商品のデータを含むオブジェクト.
	 * @return 処理対応件数.
	 */
	@Update({"UPDATE item",
		"SET itemName= #{itemName}, itemPrice = #{itemPrice}, message = #{message}, salesDateTime = #{salesDateTime}",
		"WHERE itemId = #{itemId}"})
	public int update(Item item);
	
	/**
	 * ある商品をテーブルから削除するためのメソッド.
	 * @param itemId 削除したい商品のID.
	 * @return 処理対応件数
	 */
	@Delete({"DELETE FROM item", "WHERE itemId = #{itemId}"})
	public int delete(String itemId);
	
	/**
	 * 商品がカートに入れられているかどうかを変更するメソッド.Trueならカートの中,Falseなら販売中.
	 * @param itemId 変更したい商品のID.
	 * @param inCart 変更後の状態.
	 * @return 処理対応件数.
	 */
	@Update({
		"UPDATE item",
	    "SET inCart = #{inCart} WHERE itemId = #{itemId}"
	})
	
	public int updateInCart(String itemId, boolean inCart);
	/**
	 * 商品の販売状況を変更する. Trueなら販売中,Falseなら完売.
	 * @param itemId 変更したい商品のID.
	 * @param isSold 変更後の状況.
	 * @return 処理対応件数.
	 */
	@Update({
	    "UPDATE item",
	    "SET isSold = #{isSold} WHERE itemId = #{itemId}"
	})
	public int updateIsSold(String itemId, boolean isSold);
	
	//新規追加
	
	/**
	 * 商品の受け取り確認状態を更新するメソッド。Trueなら受け取り完了。
	 * @param itemId 変更したい商品のID。
	 * @param isCompletion 変更後の状態。
	 * @return 処理対応件数。
	 */
	@Update({
	    "UPDATE item",
	    "SET isCompletion = #{isCompletion} WHERE itemId = #{itemId}"
	})
	public int updateIsCompletion(String itemId, boolean isCompletion);

}