package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Orders;

/**
 * ordersテーブルを操作するためのクラス.
 * @author 大西竣介
 */
@Mapper
@Repository
public interface OrdersMapper {
	/**
	 * ordersテーブルからすべての注文履歴を取得するためのメソッド.
	 * @return Orders型のリスト.すべての注文履歴が含まれている.
	 */
	@Select("SELECT * FROM orders")
	public List<Orders> selectAll();
	
	/**
	 * ordersテーブルからorderIdを用いて注文履歴を取得するためのメソッド.
	 * @param orderId 取得したい注文履歴のid.
	 * @return Orders型のオブジェクト.1件の注文履歴が含まれている.
	 */
	@Select("SELECT * FROM orders WHERE orderId = #{orderId}")
	public Orders selectByOrderId(String orderId);
	
	/**
	 * ordersテーブルからitemIdを用いて注文履歴を取得するためのメソッド.
	 * @param itemId 取得したい商品のId.
	 * @return Orders型のオブジェクト.1件の注文履歴が含まれている.
	 */
	@Select("SELECT * FROM orders WHERE itemId = #{itemId}")
	public Orders selectByItemId(String itemId);
	
	/**
	 * ordersテーブルからornerIdを用いて注文履歴を取得するためのメソッド.
	 * @param ornerId 取得したい販売者のユーザID.
	 * @return Orders型のリスト.
	 */
	@Select("SELECT * FROM orders WHERE ornerId = #{ornerId}")
	public List<Orders> selectByOrnerId(String ornerId);
	
	/**
	 * ordersテーブルからpurchaserIdを用いて注文履歴を取得するためのメソッド.
	 * @param ornerId 取得したい購入者のユーザID.
	 * @return Orders型のリスト.
	 */
	@Select("SELECT * FROM orders WHERE ornerId = #{purchaserId}")
	public List<Orders> selectByPurchaserId(String purchaser);
	
	/**
	 * ordersテーブルに新しい注文履歴を挿入するためのメソッド.
	 * @param orders 挿入したい注文履歴.
	 * @return 処理対応件数.
	 */
	@Insert({"INSERT INTO orders(orderId, itemId, ornerId, purchaserId, orderDateTime)",
	"VALUES(#{orderId}, #{itemId}, #{ornerId}, #{purchaserId}, #{orderDateTime})"})
	public int insert(Orders orders);
	
	/**
	 * ordersテーブルから注文履歴を削除するためのメソッド.
	 * @param orderId 削除したい注文履歴のid.
	 * @return 処理対応件数.
	 */
	@Delete("DELETE FROM orders WHERE orderId = #{orderId}")
	public int delete(String orderId);
}
