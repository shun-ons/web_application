package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.service.impl.UserServiceImpl;
import com.example.demo.entity.Item;
import com.example.demo.input.ItemInput;
import com.example.demo.repository.ItemMapper;

import lombok.RequiredArgsConstructor;

/**
 * ItemMapperクラスを呼び出して,itemテーブルを操作するためのクラス.
 * @author 大西竣介
 */
@RequiredArgsConstructor
@Transactional
@Service
public class ItemService {
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private UserServiceImpl userService;
	
	
	/**
	 * すべての商品を取得するためのメソッド.
	 * @return Item型のリストが返される.全ての商品が格納されている.
	 */
	public List<Item> selectAll() {
		return itemMapper.selectAll();
	}
	
	/**
	 * itemIdを用いて商品を取得するメソッド.
	 * @param itemId 取得したい商品のID.
	 * @return Item型のオブジェクト.取得したい商品のデータが含まれている.
	 */
	public Item selectById(String itemId) {
		return itemMapper.selectById(itemId);
	}
	
	/**
	 * orderIdを用いて,あるユーザが出品した商品を取得するためのメソッド.
	 * @param ornerId あるユーザのID.これを用いて商品を検索する.
	 * @return Item型のリスト.引数で指定したユーザが出品した商品が含まれている.
	 */
	public List<Item> selectByOrnerId(String ornerId) {
		return itemMapper.selectByOrnerId(ornerId);
	}
	
	
	/**
	 *  登録と更新のうち、共通する動作をまとめたメソッド.
	 * @param itemInput ユーザに入力された値を含むオブジェクト.
	 * @param userId 商品を販売するユーザ.
	 * @return 変更後の商品のオブジェクト.
	 */
	private Item edit(ItemInput itemInput, String userId) {
		Item item = new Item();
		if (itemInput.getItemId() == null) {
			item.setId(UUID.randomUUID().toString());
		} else {
			item.setId(itemInput.getItemId());
		}
		item.setItemName(itemInput.getItemName());
		item.setItemPrice(itemInput.getItemPrice());
		item.setOrnerName(userService.getUserName(userId));
		item.setOrnerId(userId);
		item.setComment(itemInput.getComment());
		LocalDateTime now = LocalDateTime.now();
		item.setSalesDateTime(now);
		
		return item;
	}
	
	/**
	 *  データベースに商品を登録するためのメソッド.
	 * @param itemInput ユーザに入力された値を含むオブジェクト.
	 * @param userId　userId 商品を販売するユーザ.
	 * @return　変更後の商品のオブジェクト.
	 */
	public Item sell(ItemInput itemInput, String userId) {
		Item item = this.edit(itemInput, userId);
		itemMapper.insert(item);
		return item;
	}
	
	/**
	 * データベースに登録された商品の詳細を更新するためのメソッド.
	 * @param itemInput ユーザに入力された値を含むオブジェクト.
	 * @param userId 商品を販売するユーザ.
	 * @return 変更後の商品のオブジェクト.
	 */
	public ItemInput update(ItemInput itemInput, String userId) {
		Item item = this.edit(itemInput, userId);
		itemMapper.update(item);
		return itemInput;
	}
	
	/**
	 *  データベースから商品を削除するためのメソッド.
	 *  @param itemId 削除したい商品のID.
	 */
	public void delete(String itemId) {
		itemMapper.delete(itemId);
	}
	
	/**
	 * カートに入っているかどうかを変更するためのメソッド.
	 * @param itemId 変更したい商品のID.
	 * @param inCart 変更後の状態.
	 */
	public void updateInCart(String itemId, boolean inCart) {
		itemMapper.updateInCart(itemId, inCart);
	}
	
	/**
	 *  商品の販売状況を変更するためのメソッド.
	 * @param itemId 変更したい商品のID.
	 * @param isSold 変更後の商品の状態.
	 */
	public void  updateIsSold(String itemId, boolean isSold) {
		itemMapper.updateIsSold(itemId, isSold);
	}
	
	/**
	 *  ItemのリストをItemInputのリストに変換するメソッド.
	 * @param items 変更したいItemのリスト.
	 * @return 引数を変換したItemInput.
	 */
	public List<ItemInput> turnItemIntoItemInput(List<Item> items) {
		List<ItemInput> itemInputList = new ArrayList<ItemInput>();
		for (int i = 0; i < items.size(); i++) {
			Item itemTmp = items.get(i);
			ItemInput itemInputTmp = new ItemInput(itemTmp.getItemName(), itemTmp.getItemPrice(), itemTmp.getComment());
			itemInputTmp.setItemId(itemTmp.getItemId());
			itemInputList.add(itemInputTmp);
		}
		return itemInputList;
	}
	
	/**
	 *  保存している写真をimageディレクトリから削除するメソッド.
	 *  Itemテーブルとは関係ないが,呼び出す際に便利なのでこのクラスに記述している.
	 * @param itemId 削除したい写真の商品のID.
	 * @return 写真の削除が成功するとTrue,失敗するとFalseを返す.
	 */
	public boolean deleteImage(String itemId) {
		String imageName = "src/main/resources/static/image/" + itemId + ".png";
		Path filePath = Paths.get(imageName).normalize();
		try {
            // ファイルが存在するかチェック
            if (Files.exists(filePath)) {
                Files.delete(filePath);  // ファイルを削除
                return true;
            } else {
            	return false;
            }
		} catch (IOException e) {
			return false;
		}
	}
}
