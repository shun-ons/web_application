package com.example.demo.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.ReservingAppt;

/**
 * reservingApptテーブルを操作するためのクラス.
 * @author 大西竣介
 */
public interface ReservingApptMapper {
	/**
	 * ある商品の受け取り予約を取得する.
	 * @param itemId 取得したい商品のID.
	 * @return ReservingAppt型のオブジェクト.
	 */
	@Select("SELECT * FROM reservingAppt WHERE itemId = #{itemId}")
	public ReservingAppt selectByItemId(String itemId);
	
	/**
	 * テーブルに新たな行を追加するためのメソッド.
	 * @param reservingAppt 受け取り予約の入力.
	 * @return 処理対応件数.
	 */
	@Insert({"INSERT INTO reservingAppt",
		"(reservingApptId, itemId, place1, date1, time1, place2, date2, time2, place3, date3, time3, reservingApptDateTime)",
		"VALUES(#{reservingApptId}, #{itemId}, #{place1}, #{date1}, #{time1}, #{place2}, #{date2}, #{time2}, #{place3}, #{date3}, #{time3}, #{reservingApptDateTime})"})
	public int insert(ReservingAppt reservingAppt);
	
	/**
	 * テーブルに新たな行を追加するためのメソッド.
	 * @param reservingAppt 受け取り予約の入力.
	 * @return 処理対応件数.
	 */
	@Update({"UPDATE reservingAppt",
		"set reservingApptId=#{reservingApptId}, place1=#{place1}, date1=#{date1}, time1=#{time1}, ",
		"place2=#{place2}, date2=#{date2}, time2=#{time2}, place3=#{place3}, date3=#{date3}, time3=#{time3}, reservingApptDateTime=#{reservingApptDateTime}",
		"WHERE itemId=#{itemId}"})
	public int update(ReservingAppt reservingAppt);
	/**
	 * テーブルからitemIdが一致する行を削除するメソッド.
	 * @param itemId 削除したい商品のID.
	 * @return 処理対応件数.
	 */
	@Delete("DELETE FROM reservingAppt WHERE itemId = #{itemId}")
	public int delete(String itemId);
}
