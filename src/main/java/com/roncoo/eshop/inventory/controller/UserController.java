package com.roncoo.eshop.inventory.controller;

import com.roncoo.eshop.inventory.model.User;
import com.roncoo.eshop.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Component:
 * Description:
 * Date: 17/6/30
 *
 * @author yue.zhang
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo(){
        return userService.findUserInfo();
    }


    @RequestMapping("/getCachedUserInfo")
    @ResponseBody
    public User getCachedUserInfo(){
        return userService.getCachedUserInfo();
    }
}
