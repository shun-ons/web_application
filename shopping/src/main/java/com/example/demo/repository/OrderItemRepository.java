package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.OrderItem;

/**
 * 注文アイテムを管理するリポジトリインターフェース。
 * データベースとのやり取りを行う。
 * @author 石井叶輝
 */
@Mapper
public interface OrderItemRepository {

    /**
     * 指定された注文IDに商品を追加するメソッド。
     * 
     * @param orderId 注文ID（ユニークなID）
     * @param itemId 商品ID
     * @param purchaserId 購入者ID
     * @param ornerId 販売者ID
     * @param priceAtOrder 注文時の価格
     */
    @Insert("""
        INSERT INTO order_item (orderId, itemId, purchaserId, ornerId, priceAtOrder, orderDateTime)
        VALUES (#{orderId}, #{itemId}, #{purchaserId}, #{ornerId}, #{priceAtOrder}, CURRENT_TIMESTAMP)
        """)
    void addItemToOrder(@Param("orderId") String orderId,
                        @Param("itemId") String itemId,
                        @Param("purchaserId") String purchaserId,
                        @Param("ornerId") String ornerId,
                        @Param("priceAtOrder") int priceAtOrder);

    /**
     * 指定された購入者IDに紐づくすべての商品を取得するメソッド。
     * 
     * @param purchaserId 購入者ID
     * @return 購入者が購入した商品のリスト
     */
    @Select("""
        SELECT i.*
        FROM order_item o
        INNER JOIN item i ON o.itemId = i.itemId
        WHERE o.purchaserId = #{purchaserId}
        """)
    List<OrderItem> findAllItemsByPurchaserId(@Param("purchaserId") String purchaserId);

    /**
     * 指定された購入者IDの合計注文金額を計算するメソッド。
     * 
     * @param purchaserId 購入者ID
     * @return 合計注文金額
     */
    @Select("""
        SELECT SUM(priceAtOrder)
        FROM order_item
        WHERE purchaserId = #{purchaserId}
        """)
    Integer calculateTotalPrice(@Param("purchaserId") String purchaserId);

    /**
     * 指定された購入者IDと商品IDに基づいて注文商品を削除するメソッド。
     * 
     * @param purchaserId 購入者ID
     * @param itemId 商品ID
     */
    @Delete("""
        DELETE FROM order_item
        WHERE purchaserId = #{purchaserId} AND itemId = #{itemId}
        """)
    void removeItemFromOrder(@Param("purchaserId") String purchaserId,
                             @Param("itemId") String itemId);

    /**
     * カート内の全商品を orders テーブルに移動するメソッド。
     * 
     * @param orderId 注文ID（ユニークなID）
     * @param itemId 商品ID
     * @param purchaserId 購入者ID
     * @param ornerId 販売者ID
     */
    @Insert("""
        INSERT INTO orders (orderId, itemId, purchaserId, ornerId, orderDateTime)
        VALUES (#{orderId}, #{itemId}, #{purchaserId}, #{ornerId}, CURRENT_TIMESTAMP)
        """)
    void moveToOrders(@Param("orderId") String orderId,
                      @Param("itemId") String itemId,
                      @Param("purchaserId") String purchaserId,
                      @Param("ornerId") String ornerId);

    /**
     * 購入者IDを基に、カート内の商品をすべて削除するメソッド。
     * 
     * @param purchaserId 購入者ID
     */
    @Delete("""
        DELETE FROM order_item
        WHERE purchaserId = #{purchaserId}
        """)
    void clearCartItems(@Param("purchaserId") String purchaserId);
}
