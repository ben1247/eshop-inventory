package com.roncoo.eshop.inventory.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.roncoo.eshop.inventory.dao.RedisDao;
import com.roncoo.eshop.inventory.mapper.UserMapper;
import com.roncoo.eshop.inventory.model.User;
import com.roncoo.eshop.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Component:
 * Description:
 * Date: 17/6/30
 *
 * @author yue.zhang
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisDao redisDao;

    @Override
    public User findUserInfo() {
        return userMapper.findUserInfo();
    }

    @Override
    public User getCachedUserInfo() {

        redisDao.set("cached_user_lisi", "{\"name\": \"lisi\", \"age\":28}");

        String userJSON = redisDao.get("cached_user_lisi");
        JSONObject userJSONObject = JSONObject.parseObject(userJSON);

        User user = new User();
        user.setName(userJSONObject.getString("name"));
        user.setAge(userJSONObject.getInteger("age"));

        return user;
    }
}
