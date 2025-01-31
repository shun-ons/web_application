package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;
import com.example.demo.entity.Item;
import com.example.demo.input.ReservingApptInput;
import com.example.demo.service.CartService;
import com.example.demo.service.ReservingApptService;

/**
 * カート機能を管理するコントローラークラス。
 * @author 石井叶輝
 */
@Controller
public class CartController {
    
    private final UserService userService;
    private final CartService cartService;
    private final ReservingApptService reservingApptService;

    /**
     * コンストラクタ
     * 
     * @param cartService カートサービス
     * @param userService ユーザーサービス
     * @param reservingApptService 予約サービス
     */
    public CartController(CartService cartService, UserService userService, ReservingApptService reservingApptService) {
        this.cartService = cartService;
        this.userService = userService;
        this.reservingApptService = reservingApptService;
    }

    /**
     * カートに商品を追加する処理。
     * 成功時は確認画面にメッセージを表示。
     * 
     * @param userId ユーザーID
     * @param itemId 商品ID
     * @param model モデル
     * @return 確認画面のビュー名
     */
    @PostMapping("/cart/add")
    public String addToCart(String userId, String itemId, Model model) {
        try {
            cartService.addItemToCart(userId, itemId);
            model.addAttribute("message", "商品がカートに追加されました");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);
        return "cart/addConfirmation";
    }

    /**
     * カートの内容を表示する処理。
     * 
     * @param userId ユーザーID
     * @param model モデル
     * @return カート表示画面のビュー名
     */
    @PostMapping("/cart/view")
    public String viewCart(String userId, Model model) {
        model.addAttribute("cartItems", cartService.getCartItems(userId));
        model.addAttribute("totalPrice", cartService.getTotalPrice(userId));
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);
        return "cart/cart";
    }

    /**
     * カートから商品を削除する処理。
     * 成功時は確認画面にメッセージを表示。
     * 
     * @param userId ユーザーID
     * @param itemId 商品ID
     * @param model モデル
     * @return 確認画面のビュー名
     */
    @PostMapping("/cart/remove")
    public String removeFromCart(String userId, String itemId, Model model) {
        try {
            cartService.removeItemFromCart(userId, itemId);
            model.addAttribute("message", "商品を削除しました");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);
        return "cart/removeConfirmation";
    }

    /**
     * 予約画面を表示する処理。
     * 
     * @param userId ユーザーID
     * @param model モデル
     * @return 予約画面のビュー名
     */
    @PostMapping("/cart/appt")
    public String reservingAppt(String userId, Model model) {
    	List<Item> items = cartService.getCartItems(userId);
    	Date date = new Date();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	String strDate = dateFormat.format(date);
    	MUser muser = userService.getUserOne(userId);
    	model.addAttribute("muser", muser);
    	model.addAttribute("items", items);
    	model.addAttribute("today", strDate);
    	model.addAttribute("recall", false);
    	return "cart/reservingAppt";
    }

    /**
     * 予約内容を確認し、予約情報を保存する処理。
     * 
     * @param allParams リクエストパラメータ
     * @param model モデル
     * @return 予約確認画面のビュー名
     */
    @PostMapping("/cart/appt/confirm")
    public String reservingApptConfirmation(@RequestParam Map<String, String> allParams, Model model) {
    	String userId = allParams.get("userId");
    	allParams.forEach((key, value) -> System.out.println(key + " = " + value));
    	List<Item>cartItems = cartService.getCartItems(userId);
    	List<ReservingApptInput> reservingApptInputs = new ArrayList<ReservingApptInput>();
    	Map<String, String> itemNameMap = new HashMap<String, String>();
    	int count = 0;
    	if (allParams.get("recall").equals("true")) {
    		cartItems.add(new Item());
    	}
    	for (Item cartItem : cartItems) {
    		String itemId = allParams.get("itemId");
    		ReservingApptInput reservingApptInput = new ReservingApptInput();
    		reservingApptInput.setItemId(itemId);
    		reservingApptInput.setPlace1(allParams.get(itemId+".place1"));
    		reservingApptInput.setDate1(allParams.get(itemId+".date1"));
    		reservingApptInput.setTime1(allParams.get(itemId+".time1"));
    		reservingApptInput.setPlace2(allParams.get(itemId+".place2"));
    		reservingApptInput.setDate2(allParams.get(itemId+".date2"));
    		reservingApptInput.setTime2(allParams.get(itemId+".time2"));
    		reservingApptInput.setPlace3(allParams.get(itemId+".place3"));
    		reservingApptInput.setDate3(allParams.get(itemId+".date3"));
    		reservingApptInput.setTime3(allParams.get(itemId+".time3"));
    		reservingApptInputs.add(reservingApptInput);
    		
    		if (reservingApptService.findByItemId(itemId) == null) {
    			reservingApptService.add(reservingApptInput);
    		} else {
    			reservingApptService.update(reservingApptInput);
    		}
    		itemNameMap.put("name"+count, cartItem.getItemName());
    	}
    	System.out.println(reservingApptInputs.size());
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);
        model.addAttribute("reservingApptInputs", reservingApptInputs);
        model.addAttribute("itemNameMap", itemNameMap);

        model.addAttribute("select",allParams.get("recall"));
    	return "cart/reservingApptConfirmation";
    }

    /**
     * 予約入力画面に戻る処理。
     * 予約データを削除し、元の画面を表示する。
     * 
     * @param userId ユーザーID
     * @param model モデル
     * @return 予約入力画面のビュー名
     */
    @PostMapping(value = "/payment/check", params = "delete")
    public String returnInput(@RequestParam String userId, @RequestParam String recall, Model model) {
    	List<Item> cartItems = cartService.getCartItems(userId);
    	for (Item cartItem : cartItems) {
    		String itemId = cartItem.getItemId();
    		reservingApptService.delete(itemId);
    	}
    	Date date = new Date();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	String strDate = dateFormat.format(date);
    	MUser muser = userService.getUserOne(userId);
    	model.addAttribute("muser", muser);
    	model.addAttribute("items", cartItems);
    	model.addAttribute("today", strDate);
    	model.addAttribute("recall", recall);
    	return "cart/reservingAppt";
    }
}
