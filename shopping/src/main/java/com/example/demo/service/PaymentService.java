package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Item;
import com.example.demo.repository.UserMapper;

@Service
public class PaymentService {

    private final CartService cartService;
    private final UserMapper userMapper;
    private final OrdersService ordersService;
    private final ItemService itemService;

    public PaymentService(CartService cartService, UserMapper userMapper, OrdersService ordersService, ItemService itemService) {
        this.cartService = cartService;
        this.userMapper = userMapper;
        this.ordersService = ordersService;
        this.itemService = itemService;
    }

    /**
     * 決済処理を実行。
     * @param userId ユーザーID
     * @return 
     */
    public List<String> processPayment(String userId) {
        // カートの合計金額を取得
        int totalPrice = cartService.getTotalPrice(userId);

        // ユーザーの現在のポイントを取得
        int userPoint = userMapper.findPointByUserId(userId);

        // 残高不足の場合は例外をスロー
        if (userPoint < totalPrice) {
            throw new IllegalArgumentException("ポイントが不足しています");
        }

        // ポイントを減算（購入者の残高から引く）
        int updatedPoint = userPoint - totalPrice;
        userMapper.updateUserPoint(userId, updatedPoint);
        
        List<Item> items = cartService.getCartItems(userId);
        List<String> ordersIds = new ArrayList<>();
        
        for (Item item: items) {
            String itemId = item.getItemId();
            String sellerId = item.getOrnerId(); // 販売者のID
            int itemPrice = item.getItemPrice();

            // 購入履歴を作成
            String ordersId = ordersService.addOrders(itemId, sellerId, userId);
            ordersIds.add(ordersId);

            // 商品の販売状況を変更
            itemService.updateIsSold(itemId, false);

            // 販売者の残高を更新（商品価格の97%を加算）
            int sellerCurrentBalance = userMapper.findPointByUserId(sellerId);
            int sellerUpdatedBalance = sellerCurrentBalance + (int) (itemPrice * 0.97);
            userMapper.updateUserPoint(sellerId, sellerUpdatedBalance);
        }

        // 決済完了後の処理（例: カートのクリア）
        cartService.clearCart(userId);

        return ordersIds;
    }

}
