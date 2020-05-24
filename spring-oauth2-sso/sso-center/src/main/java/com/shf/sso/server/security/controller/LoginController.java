package com.shf.sso.server.security.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/12/27 17:58
 * @Version V1.0
 **/
@Api(value = "LoginController", tags = "LoginController", description = "登录接口")
@RestController
public class LoginController {
    @Autowired
    private TokenStore tokenStore;

    @ApiOperation("登出")
    @RequestMapping(value = "/oauth/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String token(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.replace("Bearer ", "");
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(accessToken);
        tokenStore.removeAccessTokenUsingRefreshToken(accessToken.getRefreshToken());
        tokenStore.removeRefreshToken(accessToken.getRefreshToken());
        return "OK";
    }

//    @ApiOperation("登录")
//    @RequestMapping(value = "/oauth/token", method = {RequestMethod.GET, RequestMethod.POST})
//    public String login(String username, String password, String scope, String grant_type, String client_id, String client_secret) {
//        return "login";
//    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

}
