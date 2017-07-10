package com.roncoo.eshop.inventory.service;

import com.roncoo.eshop.inventory.model.User;

/**
 * Component:
 * Description:
 * Date: 17/6/30
 *
 * @author yue.zhang
 */
public interface UserService {

    /**
     * 查询用户信息
     * @return
     */
    User findUserInfo();

    /**
     * 查询redis中缓存的用户信息
     * @return
     */
    User getCachedUserInfo();
}
