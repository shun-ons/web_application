package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.repository.UserMapper;
import com.example.demo.service.CartService;
import com.example.demo.service.PaymentService;

@Controller
public class PaymentController {

    private final CartService cartService;
    private final PaymentService paymentService;
    private final UserMapper userMapper;

    public PaymentController(CartService cartService, PaymentService paymentService, UserMapper userMapper) {
        this.cartService = cartService;
        this.paymentService = paymentService;
        this.userMapper = userMapper;
    }

    /**
     * 決済確認画面の表示。
     * カート内の合計金額とユーザーのポイントを計算して表示。
     */
    @PostMapping("/payment/check")
    public String showPaymentCheck(String userId, Model model) {
        // ユーザーのポイントを取得
        int userPoint = userMapper.findPointByUserId(userId);
        // カートの合計金額を取得
        int totalPrice = cartService.getTotalPrice(userId);

        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("userId", userId);
        model.addAttribute("point", userPoint);

        return "payment/paymentCheck"; // 決済確認画面を表示
    }

    /**
     * 決済処理を実行し、成功画面を表示。
     */
    @PostMapping("/payment/complete")
    public String completePayment(String userId, Model model) {
        try {
            // 決済処理を実行（ポイント減算を含む）
            paymentService.processPayment(userId);

            // 決済成功メッセージを設定
            model.addAttribute("message", "決済が完了しました\nまたのご利用をお待ちしております。");
        } catch (Exception e) {
            // 決済失敗時にエラーメッセージを表示
            model.addAttribute("error", e.getMessage());
            return "payment/paymentCheck"; // 再度確認画面を表示
        }

        // 更新後のユーザーのポイントを取得
        int updatedPoint = userMapper.findPointByUserId(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("point", updatedPoint);

        return "payment/paymentSuccess"; // 決済成功画面を表示
    }
}
