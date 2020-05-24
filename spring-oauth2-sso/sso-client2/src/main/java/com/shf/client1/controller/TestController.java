package com.shf.client1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2020/5/24 18:51
 * @Version V1.0
 **/
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
