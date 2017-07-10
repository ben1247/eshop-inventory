package com.roncoo.eshop.inventory.service.impl;

import com.roncoo.eshop.inventory.dao.RedisDao;
import com.roncoo.eshop.inventory.mapper.ProductInventoryMapper;
import com.roncoo.eshop.inventory.model.ProductInventory;
import com.roncoo.eshop.inventory.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Component:
 * Description:
 * Date: 17/7/3
 *
 * @author yue.zhang
 */
@Service
public class ProductInventoryServiceImpl implements ProductInventoryService{

    private final String cacheKey = "product:inventory:";

    @Autowired
    private ProductInventoryMapper productInventoryMapper;

    @Autowired
    private RedisDao redisDao;


    @Override
    public void update(ProductInventory productInventory) {
        productInventoryMapper.update(productInventory);
        System.out.println("===========日志===========: 已修改数据库中的库存，商品id=" + productInventory.getProductId() + ", 商品库存数量=" + productInventory.getInventoryCnt());
    }

    @Override
    public void removeCache(ProductInventory productInventory) {
        String key = createKey(productInventory.getProductId());
        redisDao.delete(key);
        System.out.println("===========日志===========: 已删除redis中的缓存，key=" + key);
    }

    @Override
    public ProductInventory findByProductId(Integer productId) {
        return productInventoryMapper.findByProductId(productId);
    }

    @Override
    public void setCache(ProductInventory productInventory) {
        String key = createKey(productInventory.getProductId());
        redisDao.set(key,String.valueOf(productInventory.getInventoryCnt()));
        System.out.println("===========日志===========: 已更新商品库存的缓存，商品id=" + productInventory.getProductId() + ", 商品库存数量=" + productInventory.getInventoryCnt() + ", key=" + key);
    }

    @Override
    public ProductInventory getByCache(Integer productId) {
        String key = createKey(productId);
        String result = redisDao.get(key);
        if(result != null && !"".equals(result)){
            try {
                Long inventoryCnt = Long.valueOf(result);
                return new ProductInventory(productId,inventoryCnt);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    private String createKey(Integer productId){
        return cacheKey + productId;
    }
}
