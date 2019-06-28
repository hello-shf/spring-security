package com.shf.jwt.controller;

import com.shf.jwt.entity.TUser;
import com.shf.jwt.service.TUserService;
import com.shf.jwt.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：用户controller
 *
 * @author: shf
 * @date: 2019-04-18 15:23:42
 * @version: V1.0
 */
@RestController
@RequestMapping("/user")
public class TUserController {
    @Autowired
    private TUserService userService;

    @RequestMapping("/findOne")
    public TUser findOne(String username){
        return userService.findByName(username);
    }

    @RequestMapping("/test")
    public Response test(String username){
        Response response = new Response();
        return response.buildSuccessResponse();
    }
}
