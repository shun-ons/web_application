package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.repository.UserMapper;
import com.example.demo.service.CartService;
import com.example.demo.service.ItemService;
import com.example.demo.service.NotificationService;
import com.example.demo.service.PaymentService;

@Controller
public class PaymentController {

    private final CartService cartService;
    private final PaymentService paymentService;
    private final UserMapper userMapper;
    private final NotificationService notificationService;
    private final ItemService itemService;

    public PaymentController(CartService cartService, PaymentService paymentService, UserMapper userMapper, NotificationService notificationService, ItemService itemService) {
        this.cartService = cartService;
        this.paymentService = paymentService;
        this.userMapper = userMapper;
        this.notificationService = notificationService;
        this.itemService = itemService;
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
            List<String> ordersIds = paymentService.processPayment(userId);
            // 通知を作成.
            for (String ordersId : ordersIds) {
            	notificationService.addNotification(ordersId);
            }
            // 決済成功メッセージを設定
            model.addAttribute("message", "決済が完了しました。またのご利用をお待ちしております。");
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
