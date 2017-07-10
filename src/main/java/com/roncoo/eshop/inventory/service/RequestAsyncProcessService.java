package com.roncoo.eshop.inventory.service;

import com.roncoo.eshop.inventory.request.Request;

/**
 * Component: 请求异步执行service
 * Description:
 * Date: 17/7/3
 *
 * @author yue.zhang
 */
public interface RequestAsyncProcessService {

    void process(Request request);

}
