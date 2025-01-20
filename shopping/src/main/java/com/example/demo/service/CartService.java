package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemMapper;
import com.example.demo.repository.OrderItemRepository;

@Service
public class CartService {

    private final OrderItemRepository orderItemRepository;
    private final ItemMapper itemMapper;

    public CartService(OrderItemRepository orderItemRepository, ItemMapper itemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.itemMapper = itemMapper;
    }

    /**
     * 商品をカートに追加する。
     * 商品情報を取得し、カートに追加して販売ステータスを更新する。
     */
    @Transactional
    public void addItemToCart(String purchaserId, String itemId) {
        // 商品を取得
        Item item = itemMapper.selectById(itemId);
        if (item == null || item.getInCart()) {
            throw new IllegalArgumentException("無効な商品IDまたは既に販売済みの商品です");
        }

        // カートに追加
        String orderId = UUID.randomUUID().toString();
        orderItemRepository.addItemToOrder(orderId, itemId, purchaserId, item.getOrnerId(), item.getItemPrice());

        // inCart を true に設定（販売済みとしてマーク）
        itemMapper.updateInCart(itemId, true);
    }
    
    /**
     * カートから商品を削除する。
     * カートから削除後、商品の販売ステータスを未販売に戻す。
     */
    @Transactional
    public void removeItemFromCart(String purchaserId, String itemId) {
        // カートから商品を削除
        orderItemRepository.removeItemFromOrder(purchaserId, itemId);

        // 商品の販売ステータスを元に戻す（未販売に設定）
        itemMapper.updateInCart(itemId, false);
    }

    /**
     * 購入者IDを基にカート内の商品を取得する。
     */
    public List<Item> getCartItems(String purchaserId) {
        return orderItemRepository.findAllItemsByPurchaserId(purchaserId);
    }

    /**
     * 購入者IDを基にカート内の商品価格の合計を取得する。
     */
    public int getTotalPrice(String purchaserId) {
        Integer totalPrice = orderItemRepository.calculateTotalPrice(purchaserId);
        return totalPrice != null ? totalPrice : 0;
    }

    /**
     * カート内の全商品を orders テーブルに移動し、カートをクリアする。
     * 
     * @param purchaserId 購入者ID
     */
    @Transactional
    public void clearCart(String purchaserId) {
        // 購入者IDに基づいてカート内の商品を取得
        List<Item> cartItems = orderItemRepository.findAllItemsByPurchaserId(purchaserId);
        
        // カート内の商品を orders テーブルに移動
        for (Item item : cartItems) {
            String orderId = UUID.randomUUID().toString();
            orderItemRepository.moveToOrders(orderId, item.getItemId(), purchaserId, item.getOrnerId());
        }
        
        // カート内の商品をすべて削除
        orderItemRepository.clearCartItems(purchaserId);
    }
}
