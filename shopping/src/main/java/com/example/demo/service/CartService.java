package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Item;
import com.example.demo.entity.OrderItem;
import com.example.demo.repository.OrderItemRepository;

/**
 * カートの管理を行うサービスクラス。
 * @author 石井叶輝
 */
@Service
public class CartService {

    private final OrderItemRepository orderItemRepository;
    private final ItemService itemService;

    /**
     * コンストラクタ
     * 
     * @param orderItemRepository 注文アイテムリポジトリ
     * @param itemService 商品サービス
     */
    public CartService(OrderItemRepository orderItemRepository, ItemService itemService) {
        this.orderItemRepository = orderItemRepository;
        this.itemService = itemService;
    }

    /**
     * 商品をカートに追加する。
     * 商品情報を取得し、カートに追加して販売ステータスを更新する。
     * 
     * @param purchaserId 購入者ID
     * @param itemId 商品ID
     */
    @Transactional
    public void addItemToCart(String purchaserId, String itemId) {
        // 商品を取得
        Item item = itemService.selectById(itemId);
        if (item == null || item.getInCart()) {
            throw new IllegalArgumentException("無効な商品IDまたは既に販売済みの商品です");
        }

        // カートに追加
        String orderId = UUID.randomUUID().toString();
        orderItemRepository.addItemToOrder(orderId, itemId, purchaserId, item.getOrnerId(), item.getItemPrice());

        // inCart を true に設定（販売済みとしてマーク）
        itemService.updateInCart(itemId, true);
    }
    
    /**
     * カートから商品を削除する。
     * カートから削除後、商品の販売ステータスを未販売に戻す。
     * 
     * @param purchaserId 購入者ID
     * @param itemId 商品ID
     */
    @Transactional
    public void removeItemFromCart(String purchaserId, String itemId) {
        // カートから商品を削除
        orderItemRepository.removeItemFromOrder(purchaserId, itemId);

        // 商品の販売ステータスを元に戻す（未販売に設定）
        itemService.updateInCart(itemId, false);
    }

    /**
     * 購入者IDを基にカート内の商品を取得する。
     * 
     * @param purchaserId 購入者ID
     * @return カート内の商品リスト
     */
    public List<Item> getCartItems(String purchaserId) {
        List<OrderItem> orderItems = orderItemRepository.findAllItemsByPurchaserId(purchaserId);
        List<Item> items = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            String itemId = orderItem.getItemId();
            Item item = itemService.selectById(itemId);
            items.add(item);
        }
        return items;
    }

    /**
     * 購入者IDを基にカート内の商品価格の合計を取得する。
     * 
     * @param purchaserId 購入者ID
     * @return カート内の商品価格の合計
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
        // カート内の商品をすべて削除
        orderItemRepository.clearCartItems(purchaserId);
    }
}
