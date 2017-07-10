package com.roncoo.eshop.inventory.service;

import com.roncoo.eshop.inventory.model.ProductInventory;

/**
 * Component:
 * Description:
 * Date: 17/7/3
 *
 * @author yue.zhang
 */
public interface ProductInventoryService {

    /**
     * 更新商品库存
     * @param productInventory 商品库存
     */
    void update(ProductInventory productInventory);

    /**
     * 删除Redis中的商品库存的缓存
     * @param productInventory
     */
    void removeCache(ProductInventory productInventory);

    /**
     * 根据商品id查询商品库存
     * @param productId 商品id
     * @return 商品库存
     */
    ProductInventory findByProductId(Integer productId);

    /**
     * 设置商品库存的缓存
     * @param productInventory 商品库存
     */
    void setCache(ProductInventory productInventory);

    /**
     * 获取商品库存的缓存
     * @param productId
     * @return
     */
    ProductInventory getByCache(Integer productId);
}
