package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;
import com.example.demo.entity.Notification;
import com.example.demo.service.NotificationService;

/**
 * 通知蘭へのアクセスを管理するクラス.
 * @author 大西竣介
 */
@Controller
public class NotificationController {
	private final NotificationService notificationService;
	private final UserService userService;

	/**
	 * UserServiceを初期化する.
	 * @param userService
	 */
	NotificationController(NotificationService notificationService, UserService userService) {
		this.notificationService = notificationService;
        this.userService = userService;
	}
	@PostMapping("/notification")
	public String showNotification(@RequestParam String userId, Model model) {
		List<Notification> notifications = notificationService.selectByOrnerId(userId);
		List<Notification> reverseNotifications = new ArrayList<>();
		for (int i = notifications.size() -1; i >= 0; i--) {
			reverseNotifications.add(notifications.get(i));
		}
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);
        model.addAttribute("notifications", reverseNotifications);
		return "notification/notification";
	}
}
