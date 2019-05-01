package com.shf.security.login;

import com.shf.security.utils.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/4/19 14:58
 * @Version V1.0
 **/
@RestController
public class LoginController {
    @RequestMapping("/login_error")
    public Response loginError(){
        Response response = new Response();
        response.buildSuccessResponse("登录失败");
        return response;
    }
    @RequestMapping("/login_success")
    public Response loginSuccess(){
        Response response = new Response();
        response.buildSuccessResponse("登录成功");
        return response;
    }

    @RequestMapping("/login_page")
    public Response root(){
        Response response = new Response();
        response.buildSuccessResponse("尚未登录，请登录");
        return response;
    }
}
