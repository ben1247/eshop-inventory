package com.roncoo.eshop.inventory.request;

/**
 * Component:
 * Description:
 * Date: 17/7/2
 *
 * @author yue.zhang
 */
public interface Request {

    void process();

    Integer getProductId();

    boolean isForceRefresh();
}

