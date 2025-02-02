package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;
import com.example.demo.entity.Item; // （追加）Itemエンティティをインポート
import com.example.demo.repository.UserMapper;
import com.example.demo.service.CartService;
import com.example.demo.service.ItemService; // （追加）ItemServiceをインポート
import com.example.demo.service.NotificationService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.ReservingApptService;

/**
 * 決済処理と受け取り確認を管理するコントローラークラス。
 */
@Controller
public class PaymentController {

    private final CartService cartService;
    private final PaymentService paymentService;
    private final UserMapper userMapper;
    private final NotificationService notificationService;
    private final ReservingApptService reservingApptService;
    private final UserService userService;
    private final ItemService itemService; // （追加）商品サービスを追加

    /**
     * コンストラクタ
     */
    public PaymentController(CartService cartService, PaymentService paymentService, UserMapper userMapper, 
                             NotificationService notificationService, ReservingApptService reservingApptService, 
                             UserService userService, ItemService itemService) { // （変更）ItemServiceを追加
        this.cartService = cartService;
        this.paymentService = paymentService;
        this.userMapper = userMapper;
        this.notificationService = notificationService;
        this.reservingApptService = reservingApptService;
        this.userService = userService;
        this.itemService = itemService; // （追加）
    }

    /**
     * 決済確認画面を表示する処理。
     */
    @PostMapping(value = "/payment/check", params = "confirm")
    public String showPaymentCheck(@RequestParam Map<String, String> allParams, Model model) {
        String userId = allParams.get("userId");

        int userPoint = userMapper.findPointByUserId(userId);
        int totalPrice = cartService.getTotalPrice(userId);

        model.addAttribute("totalPrice", totalPrice);
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);
        model.addAttribute("point", userPoint);

        return "payment/paymentCheck"; 
    }

    /**
     * 決済処理を実行し、成功画面を表示する処理。
     */
    @PostMapping("/payment/complete")
    public String completePayment(@RequestParam String userId, Model model) {
        try {
            List<String> ordersIds = paymentService.processPayment(userId);
            for (String ordersId : ordersIds) {
                Map<String, String> orderIdMap = new HashMap<>();
                orderIdMap.put("orderId", ordersId);
                orderIdMap.put("type", "call");
                notificationService.addNotification(orderIdMap);
            }
            model.addAttribute("message", "決済が完了しました。商品を受け取り後、確認を行ってください。"); // （変更）受け取り確認を促すメッセージに変更
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "payment/paymentCheck";
        }

        int updatedPoint = userMapper.findPointByUserId(userId);
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);
        model.addAttribute("point", updatedPoint);

        return "payment/paymentSuccess";
    }

    //新規追加
    /**
     * 取引中（未完了）の商品リストを表示する処理。
     */
    @PostMapping("/transaction/list")
    public String showTransactionList(@RequestParam String userId, Model model) {
        List<Item> items = paymentService.getUncompletedPurchasedItems(userId);

        model.addAttribute("items", items);
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);
        return "payment/transaction_list";
    }
    
    //新規追加
    /**
     * 受け取り確認画面を表示する処理。
     */
    @PostMapping("/transaction/confirm")
    public String showConfirmReceipt(@RequestParam String userId, @RequestParam String itemId, Model model) {
        Item item = itemService.selectById(itemId);

        if (item == null || !item.getOrnerId().equals(userId)) {
            return "redirect:/transaction/list";
        }
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);
        model.addAttribute("item", item);
        return "payment/transaction_confirm";
    }

    //新規追加
    /**
     * 購入者が受け取り確認を行う処理。
     */
    @PostMapping("/payment/confirmReceipt")
    public String confirmReceipt(@RequestParam String userId, @RequestParam String itemId, Model model) {
        try {
            paymentService.processReceiptConfirmation(userId, itemId);
            model.addAttribute("message", "受け取りが確認されました。");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);

        return "payment/transaction_check"; // 新たなHTMLに遷移
    }

}
