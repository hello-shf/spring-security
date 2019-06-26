package com.shf.role.controller;

import com.shf.role.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/6/25 14:04
 * @Version V1.0
 **/
@Slf4j
@RestController
public class TestController {
    @RequestMapping("/admin/get")
    public Response adminGet(){
        Response response = new Response();
        response.buildSuccessResponse("admin有权限访问");
        log.info("admin有权限访问");
        return response;
    }
    @RequestMapping("/vip/get")
    public Response vipGet(){
        Response response = new Response();
        response.buildSuccessResponse("admin，vip有权限访问");
        log.info("admin，vip有权限访问");
        return response;
    }
    @RequestMapping("/user/get")
    public Response userGet(){
        Response response = new Response();
        response.buildSuccessResponse("admin，vip，user有权限访问");
        log.info("admin，vip，user有权限访问");
        return response;
    }

}
