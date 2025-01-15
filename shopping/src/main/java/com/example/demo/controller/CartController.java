package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.service.CartService;

@Controller
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * カートに商品を追加する処理。
     * 成功時は確認画面にメッセージを表示。
     */
    @PostMapping("/cart/add")
    public String addToCart(String userId, String itemId, Model model) {
        try {
            // 商品をカートに追加
            cartService.addItemToCart(userId, itemId);
            model.addAttribute("message", "商品がカートに追加されました");
        } catch (Exception e) {
            // エラーが発生した場合、エラーメッセージを表示
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("userId", userId);
        return "cart/addConfirmation"; // 確認画面を表示
    }

    /**
     * カートの内容を表示する処理。
     */
    @PostMapping("/cart/view")
    public String viewCart(String userId, Model model) {
        // カート内の商品一覧を取得してモデルに設定
        model.addAttribute("cartItems", cartService.getCartItems(userId));
        // カート内の商品の合計金額を取得してモデルに設定
        model.addAttribute("totalPrice", cartService.getTotalPrice(userId));
        model.addAttribute("userId", userId);
        return "cart/cart"; // カート表示画面を表示
    }

    /**
     * カートから商品を削除する処理。
     * 成功時は確認画面にメッセージを表示。
     */
    @PostMapping("/cart/remove")
    public String removeFromCart(String userId, String itemId, Model model) {
        try {
            // カートから商品を削除
            cartService.removeItemFromCart(userId, itemId);
            model.addAttribute("message", "商品を削除しました");
        } catch (Exception e) {
            // エラーが発生した場合、エラーメッセージを表示
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("userId", userId);
        return "cart/removeConfirmation"; // 確認画面を表示
    }
}
