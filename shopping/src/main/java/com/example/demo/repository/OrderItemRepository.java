package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Item;

@Mapper
public interface OrderItemRepository {

    /**
     * カートに商品を追加するメソッド。
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
     * 購入者IDを基に、カートに追加されたすべての商品情報を取得するメソッド。
     * @param purchaserId 購入者ID
     * @return 購入者のカート内にある商品のリスト
     */
    @Select("""
        SELECT i.*
        FROM order_item o
        INNER JOIN item i ON o.itemId = i.itemId
        WHERE o.purchaserId = #{purchaserId}
        """)
    List<Item> findAllItemsByPurchaserId(@Param("purchaserId") String purchaserId);

    /**
     * 購入者IDを基に、カート内の商品価格の合計を計算するメソッド。
     * @param purchaserId 購入者ID
     * @return 合計金額（null の場合は 0）
     */
    @Select("""
        SELECT SUM(priceAtOrder)
        FROM order_item
        WHERE purchaserId = #{purchaserId}
        """)
    Integer calculateTotalPrice(@Param("purchaserId") String purchaserId);
    
    /**
     * 購入者IDと商品IDを基に、カートから商品を削除するメソッド。
     * @param purchaserId 購入者ID
     * @param itemId 商品ID
     */
    @Delete("""
        DELETE FROM order_item
        WHERE purchaserId = #{purchaserId} AND itemId = #{itemId}
        """)
    void removeItemFromOrder(@Param("purchaserId") String purchaserId,
                             @Param("itemId") String itemId);
}
