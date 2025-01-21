package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.UserMapper;

@Service
public class PaymentService {

    private final CartService cartService;
    private final UserMapper userMapper;

    public PaymentService(CartService cartService, UserMapper userMapper) {
        this.cartService = cartService;
        this.userMapper = userMapper;
    }

    /**
     * 決済処理を実行。
     * @param userId ユーザーID
     */
    public void processPayment(String userId) {
        // カートの合計金額を取得
        int totalPrice = cartService.getTotalPrice(userId);

        // ユーザーの現在のポイントを取得
        int userPoint = userMapper.findPointByUserId(userId);

        // 残高不足の場合は例外をスロー
        if (userPoint < totalPrice) {
            throw new IllegalArgumentException("ポイントが不足しています");
        }

        // ポイントを減算
        int updatedPoint = userPoint - totalPrice;
        userMapper.updateUserPoint(userId, updatedPoint);

        // 決済完了後の処理（例: カートのクリア）
        cartService.clearCart(userId);
    }
}
