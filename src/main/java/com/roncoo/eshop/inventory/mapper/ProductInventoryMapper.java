package com.roncoo.eshop.inventory.mapper;

import com.roncoo.eshop.inventory.model.ProductInventory;
import org.apache.ibatis.annotations.Param;

/**
 * Component:
 * Description:
 * Date: 17/7/3
 *
 * @author yue.zhang
 */
public interface ProductInventoryMapper {

    /**
     * 更新库存数量
     * @param productInventory 商品库存
     */
    void update(ProductInventory productInventory);

    /**
     * 根据商品id查询商品库存信息
     * @param productId 商品id
     * @return 商品库存信息
     */
    ProductInventory findByProductId(@Param("productId") Integer productId);
}
