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
import com.example.demo.entity.Notification;
import com.example.demo.entity.ReservingAppt;
import com.example.demo.input.ReservingApptInput;
import com.example.demo.service.ItemService;
import com.example.demo.service.NotificationService;
import com.example.demo.service.OrdersService;
import com.example.demo.service.ReservingApptService;

/**
 * 通知蘭へのアクセスを管理するクラス.
 * @author 大西竣介
 */
@Controller
public class NotificationController {
	private final NotificationService notificationService;
	private final UserService userService;
	private final ReservingApptService reservingApptService;
	private final ItemService itemService;
	private final OrdersService ordersService;

	/**
	 * UserServiceを初期化する.
	 * @param userService
	 * @param notificationService
	 */
	NotificationController(NotificationService notificationService, UserService userService, ReservingApptService reservingApptService, ItemService itemService, OrdersService ordersService) {
		this.notificationService = notificationService;
        this.userService = userService;
        this.reservingApptService = reservingApptService;
        this.itemService = itemService;
        this.ordersService = ordersService;
	}
	
	/**
	 * 通知欄へのアクセスを管理するためのメソッド.
	 * @param userId
	 * @param model
	 * @return notification.htmlファイルへ遷移する.
	 */
	@PostMapping("/notification")
	public String showNotification(@RequestParam String userId, Model model) {
		List<Notification> notifications = notificationService.selectByOrnerId(userId);
		List<Notification> reverseNotifications = new ArrayList<>();
//		for (int i = notifications.size() -1; i >= 0; i--) {
//			reverseNotifications.add(notifications.get(i));
//		}
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);
        model.addAttribute("notifications", notifications);
		return "notification/notification";
	}
	
	/**
	 * 通知欄の詳細ページへアクセスするためのメソッド.
	 * @param userId
	 * @param notificationId
	 * @param model
	 * @return notification/release.htmlへ遷移する.
	 */
	@PostMapping("/notification/details")
	public String showDetails(@RequestParam String userId, @RequestParam String notificationId, Model model) {
		Notification notification = notificationService.selectByNotificationId(notificationId);
		String itemId = notification.getItemId();
		System.out.println(itemId);
		ReservingAppt reservingAppt = reservingApptService.findByItemId(itemId);
		MUser muser = userService.getUserOne(userId);
		notification.setRead_(true);
		model.addAttribute("reservingAppt", reservingAppt);
		model.addAttribute("muser", muser);
		model.addAttribute("notificationId", notificationId);
		Item item = itemService.selectById(itemId);
		
		String type = notification.getType();
		if (type.equals("call")) {
			return "notification/details";
		} else if( type.contains("response")){
			String select = String.valueOf(type.charAt(type.length()-1));
			model.addAttribute("select", select);
			model.addAttribute("itemName", item.getItemName());
			return "notification/response";
		} else {
			List<Item> items = new ArrayList<>();
			items.add(item);
			Date date = new Date();
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    	String strDate = dateFormat.format(date);
			model.addAttribute("muser", muser);
	    	model.addAttribute("items", items);
	    	model.addAttribute("today", strDate);
	    	model.addAttribute("recall", true);
	    	model.addAttribute("notificationId", notificationId);
			return "notification/reselect";
		}
	}
	
	/**
	 * 通知から受け取り日時の指定へ遷移するためのメソッド.
	 * @param allParams
	 * @param model
	 * @return notification/reselectConfirmation.htmlへ遷移する.
	 */
	@PostMapping("/notification/details/input")
	public String reselectConfirm(@RequestParam Map<String, String> allParams, Model model) {
    	String userId = allParams.get("userId");
    	allParams.forEach((key, value) -> System.out.println(key + " = " + value));
    	List<ReservingApptInput> reservingApptInputs = new ArrayList<ReservingApptInput>();
    	Map<String, String> itemNameMap = new HashMap<String, String>();
    	
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
    	
    	
    	System.out.println(reservingApptInputs.size());
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);
        model.addAttribute("reservingApptInputs", reservingApptInputs);
        model.addAttribute("itemNameMap", itemNameMap);
        model.addAttribute("recall",allParams.get("recall"));
    	return "notification/reselectConfirmation";
    }
	
	@PostMapping("/notification/details/confirmation")
	public String confirmation(@RequestParam Map<String, String> allParams, Model model) {
		// userIdを取得.
		String userId = allParams.get("userId");
		// notificationIdから通知と商品Idを取得.
		String notificationId = allParams.get("notificationId");
		Notification notification = notificationService.selectByNotificationId(notificationId);
		String itemId = notification.getItemId();
		// itemIdから受け取り予約を取得.
		ReservingAppt reservingAppt = reservingApptService.findByItemId(itemId);
		// 回答を取得.
		String answer = allParams.get("answer");
		// htmlに送信するMapを作成. 受け取り日時の内容が含まれる.
		Map<String, String> answerMap = new HashMap<String, String>();
		answerMap.put("itemName", itemService.selectById(itemId).getItemName());
		answerMap.put("answer", answer);
		answerMap.put("notificationId", notificationId);

		model.addAttribute("muser", userService.getUserOne(userId));
		model.addAttribute("itemId", itemId);
		model.addAttribute("reservingApptId", reservingAppt.getReservingApptId());
		
		if (answer.equals("1")) {
			answerMap.put("place", reservingAppt.getPlace1());
			answerMap.put("date", reservingAppt.getDate1());
			answerMap.put("time", reservingAppt.getTime1());
		} else if (answer.equals("2")) {
			answerMap.put("place", reservingAppt.getPlace2());
			answerMap.put("date", reservingAppt.getDate2());
			answerMap.put("time", reservingAppt.getTime2());
		} else if (answer.equals("3")){
			answerMap.put("place", reservingAppt.getPlace3());
			answerMap.put("date", reservingAppt.getDate3());
			answerMap.put("time", reservingAppt.getTime3());
		} else {
			String purchaserName = userService.getUserOne(notification.getPurchaserId()).getName();
			answerMap.put("type", "notFind");
			notificationService.addNotification(answerMap);
			model.addAttribute("purchaserName", purchaserName);
			return "notification/notFind";
		}
		model.addAttribute("answerMap", answerMap);
		return "/notification/confirmation";
	}
	
	@PostMapping("/notification/details/correct")
	public String correct(@RequestParam Map<String, String> allParams, Model model) {
		String userId = allParams.get("userId");
		MUser muser = userService.getUserOne(userId);
		notificationService.addNotification(allParams);
		model.addAttribute("muser", muser);
		return "notification/success";
	}
	
	/**
	 * 出品者との予定が合わなかった場合に日程の再選択をおこなうメソッド.
	 * @param userId
	 * @param model
	 * @return
	 */
	@PostMapping("/notification/reselect")
	public String reselect(String userId, Model model) {
		MUser muser = userService.getUserOne(userId);
		model.addAttribute("muser", muser);
		return "notification/notFind";
	}
	
	@PostMapping(value = "/notification/reselect", params = "confirm")
	public String reselectCorect(String userId, @RequestParam String itemId, Model model) {
		MUser muser = userService.getUserOne(userId);
		String orderId = ordersService.getOrdersByItemId(itemId).getOrderId();
		Map<String, String> orderIds = new HashMap<String, String>();
		orderIds.put("orderId", orderId);
		orderIds.put("type", "call");
		notificationService.addNotification(orderIds);
		model.addAttribute("muser", muser);
		return "notification/notFind";
	}
	
	@PostMapping(value = "/notification/reselect", params = "delete")
	public String reselectDelete(@RequestParam String userId, @RequestParam String itemId, @RequestParam String recall, Model model) {
    	reservingApptService.delete(itemId);
    	Item item = itemService.selectById(itemId);
    	List<Item> items = new ArrayList<Item>();
    	items.add(item);
    	Date date = new Date();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	String strDate = dateFormat.format(date);
    	MUser muser = userService.getUserOne(userId);
    	model.addAttribute("muser", muser);
    	model.addAttribute("items", items);
    	model.addAttribute("today", strDate);
    	model.addAttribute("recall", recall);
    	return "cart/reservingAppt";
    }
}
