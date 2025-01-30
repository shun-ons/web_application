package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ReservingAppt;
import com.example.demo.input.ReservingApptInput;
import com.example.demo.repository.ReservingApptMapper;

/**
 * reservingApptテーブルを操作するためのクラス.
 * @author 大西竣介
 */
@Service
public class ReservingApptService {
	private final ReservingApptMapper reservingApptMapper;
	
	public ReservingApptService(ReservingApptMapper reservingApptMapper) {
		this.reservingApptMapper = reservingApptMapper;
	}
	/**
	 * ある商品の受け取り予約を取得するメソッド.
	 * @param itemId 取得したい商品のID.
	 * @return ReservingAppt型のオブジェクト.
	 */
	public ReservingAppt findByItemId(String itemId) {
		return reservingApptMapper.selectByItemId(itemId);
	}
	
	/**
	 * 新しい受け取り予約を登録するためのメソッド.
	 * @param reservingApptInput ユーザが入力した受け取り予約.
	 */
	public void add(ReservingApptInput reservingApptInput) {
		ReservingAppt reservingAppt = this.turnIntoReservingAppt(reservingApptInput);
		reservingApptMapper.insert(reservingAppt);
	}
	
	public void update(ReservingApptInput reservingApptInput) {
		ReservingAppt reservingAppt = this.turnIntoReservingAppt(reservingApptInput);
		reservingApptMapper.update(reservingAppt);
	}
	
	/**
	 * 受け取り予約をテーブルから削除するためのメソッド.
	 * @param itemId
	 */
	public void delete(String itemId) {
		reservingApptMapper.delete(itemId);
	}
	
	public ReservingAppt turnIntoReservingAppt(ReservingApptInput reservingApptInput) {
		ReservingAppt reservingAppt = new ReservingAppt();
		reservingAppt.setReservingApptId(UUID.randomUUID().toString());
		reservingAppt.setItemId(reservingApptInput.getItemId());
		reservingAppt.setPlace1(reservingApptInput.getPlace1());
		reservingAppt.setDate1(reservingApptInput.getDate1());
		reservingAppt.setTime1(reservingApptInput.getTime1());
		reservingAppt.setPlace2(reservingApptInput.getPlace2());
		reservingAppt.setDate2(reservingApptInput.getDate2());
		reservingAppt.setTime2(reservingApptInput.getTime2());
		reservingAppt.setPlace3(reservingApptInput.getPlace3());
		reservingAppt.setDate3(reservingApptInput.getDate3());
		reservingAppt.setTime3(reservingApptInput.getTime3());
		reservingAppt.setReservingApptDateTime(LocalDateTime.now());
		return reservingAppt;
	}
	
	/**
	 * reservingApptをreservingApptInputに変えるメソッド.
	 * @param 変更したいReservingAppt型のオブジェクト.
	 */
	public ReservingApptInput turnIntoInput(ReservingAppt reservingAppt) {
		ReservingApptInput reservingApptInput = new ReservingApptInput();
		reservingApptInput.setItemId(reservingAppt.getItemId());
		reservingApptInput.setPlace1(reservingAppt.getPlace1());
		reservingApptInput.setDate1(reservingAppt.getDate1());
		reservingApptInput.setTime1(reservingAppt.getTime1());
		reservingApptInput.setPlace2(reservingAppt.getPlace1());
		reservingApptInput.setDate2(reservingAppt.getDate1());
		reservingApptInput.setTime2(reservingAppt.getTime1());
		reservingApptInput.setPlace3(reservingAppt.getPlace1());
		reservingApptInput.setDate3(reservingAppt.getDate1());
		reservingApptInput.setTime3(reservingAppt.getTime1());
		return reservingApptInput;
	}

}
