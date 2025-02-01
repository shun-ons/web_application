package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Item; // （追加）Itemエンティティをインポート
import com.example.demo.entity.Orders; // （追加）Ordersエンティティをインポート
import com.example.demo.repository.ItemMapper; // （追加）ItemMapperをインポート
import com.example.demo.repository.OrdersMapper; // （追加）OrdersMapperをインポート
import com.example.demo.repository.UserMapper;

/**
 * 決済処理と受け取り確認処理を管理するサービスクラス。
 */
@Service
public class PaymentService {

    private final CartService cartService;
    private final UserMapper userMapper;
    private final OrdersService ordersService;
    private final ItemService itemService;
    private final ItemMapper itemMapper;  // （追加）ItemMapper を追加
    private final OrdersMapper ordersMapper;  // （追加）OrdersMapper を追加

    /**
     * コンストラクタ
     */
    public PaymentService(CartService cartService, UserMapper userMapper, OrdersService ordersService, 
                          ItemService itemService, ItemMapper itemMapper, OrdersMapper ordersMapper) { // （変更）ItemMapper, OrdersMapper を追加
        this.cartService = cartService;
        this.userMapper = userMapper;
        this.ordersService = ordersService;
        this.itemService = itemService;
        this.itemMapper = itemMapper; // （追加）
        this.ordersMapper = ordersMapper; // （追加）
    }

    /**
     * 決済処理を実行する。
     */
    public List<String> processPayment(String userId) {
        int totalPrice = cartService.getTotalPrice(userId);
        int userPoint = userMapper.findPointByUserId(userId);

        if (userPoint < totalPrice) {
            throw new IllegalArgumentException("ポイントが不足しています");
        }

        int updatedPoint = userPoint - totalPrice;
        userMapper.updateUserPoint(userId, updatedPoint);
        
        List<Item> items = cartService.getCartItems(userId);
        List<String> ordersIds = new ArrayList<>();
        
        for (Item item : items) {
            String itemId = item.getItemId();
            String sellerId = item.getOrnerId(); 

            String ordersId = ordersService.addOrders(itemId, sellerId, userId);
            ordersIds.add(ordersId);

            itemService.updateIsSold(itemId, false); // （変更）決済時に販売者のポイント加算を削除
        }

        cartService.clearCart(userId);
        return ordersIds;
    }

    //新規追加
    /**
     * 受け取り確認処理。
     */
    public void processReceiptConfirmation(String userId, String itemId) {
        Item item = itemService.selectById(itemId);

        if (item == null || !item.getOrnerId().equals(userId)) {
            throw new IllegalArgumentException("商品が見つからないか、権限がありません。");
        }

        if (item.getIsCompletion()) {
            throw new IllegalArgumentException("すでに受け取り確認済みです。");
        }

        // 商品の受け取り確認状態を更新
        itemMapper.updateIsCompletion(itemId, true);

        // 販売者に売上を加算
        String sellerId = item.getOrnerId();
        int sellerCurrentBalance = userMapper.findPointByUserId(sellerId);
        int sellerUpdatedBalance = sellerCurrentBalance + (int) (item.getItemPrice() * 0.97);
        userMapper.updateUserPoint(sellerId, sellerUpdatedBalance);
    }

    //新規追加
    public List<Item> getUncompletedPurchasedItems(String userId) {
        // ユーザーの購入履歴を取得
        List<Orders> orders = ordersMapper.selectByPurchaserId(userId);

        // 受け取り未完了の購入した商品の ID を取得
        List<String> uncompletedItemIds = orders.stream()
                .map(Orders::getItemId)
                .collect(Collectors.toList());

        // 条件を満たす商品のみを取得して返す
        return itemService.selectAll().stream()
                .filter(item -> uncompletedItemIds.contains(item.getItemId())) // ユーザーが購入した商品
                .filter(item -> !item.getIsCompletion())  // 受け取り未完了
                .collect(Collectors.toList());
    }

}
