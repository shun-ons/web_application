package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Orders;
import com.example.demo.repository.OrdersMapper;

/**
 * OrderMapperクラスを用いてordersテーブルを操作するためのクラス.
 * @author 大西竣介
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)

public class OrdersService {
	private final OrdersMapper ordersMapper;
	
	public OrdersService(OrdersMapper ordersMapper) {
		this.ordersMapper = ordersMapper;
	}
	
	/**
	 * すべての注文履歴を取得するメソッド.
	 * @return すべての注文履歴を含むOrders型のリスト.
	 */
	public List<Orders> getAllOrders() {
		return ordersMapper.selectAll();
	}
	
	/**
	 * orderIDを用いて注文履歴を取得するメソッド
	 * @param orderId 取得したい注文履歴のID.
	 * @return 1件の注文履歴に対応するOrders型のオブジェクト.
	 */
	public Orders getOrdersByOrderId(String orderId) {
		return ordersMapper.selectByOrderId(orderId);
	}
	
	/**
	 * orderIDを用いて注文履歴を取得するメソッド
	 * @param itemId 取得したい商品のID.
	 * @return 1件の注文履歴に対応するOrders型のオブジェクト.
	 */
	public Orders getOrdersByItemId(String itemId) {
		return ordersMapper.selectByItemId(itemId);
	}
	
	/**
	 * ornerIdを用いてある販売者の注文履歴を取得するメソッド.
	 * @param ornerId 販売者のユーザID.
	 * @return 販売者が販売した商品の注文履歴.
	 */
	public List<Orders> getOrdersByOrnerId(String ornerId) {
		return ordersMapper.selectByOrnerId(ornerId);
	}
	
	/**
	 * purchaserIdを用いてある販売者の注文履歴を取得するメソッド.
	 * @param purchaserId 購入者のユーザID.
	 * @return 購入者の購入履歴.
	 */
	public List<Orders> getOrdersByPurchaserId(String purchaserId) {
		return ordersMapper.selectByPurchaserId(purchaserId);
	}
	
	/**
	 * 新しい注文履歴を追加するためのメソッド.
	 * @param itemId 購入された商品のID.
	 * @param ornerId 販売者のユーザID.
	 * @param purchaserId 購入者のユーザID. 
	 * @return 注文履歴ID
	 */
	public String addOrders(String itemId, String ornerId, String purchaserId) {
		Orders orders = new Orders();
		String ordersId = UUID.randomUUID().toString();
		orders.setOrderId(ordersId);
		orders.setItemId(itemId);
		orders.setOrnerId(ornerId);
		orders.setPurchaserId(purchaserId);
		orders.setOrderDateTime(LocalDateTime.now());
		
		try {
			System.out.println(ordersMapper.insert(orders));
			Thread.sleep(1000);
		} catch(InterruptedException e) {
			e.printStackTrace();

		}
		return ordersId;
	}
	
	/**
	 * 注文履歴を削除するためのメソッド.
	 * @param ordersId 削除したい注文履歴のID.
	 */
	public void removeOrders(String ordersId) {
		ordersMapper.delete(ordersId);
	}
}
