package com.shf.role.controller;

import com.shf.role.utils.Response;
import com.shf.role.utils.VerifyCodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

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

    @RequestMapping("/getVerifyCode")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = VerifyCodeUtil.getVerifyCode();
        HttpSession session = request.getSession();
        session.setAttribute(VerifyCodeUtil.SESSION_KEY, map.get(VerifyCodeUtil.SESSION_KEY));
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        try {
            ServletOutputStream sos = response.getOutputStream();
            ImageIO.write((RenderedImage) map.get(VerifyCodeUtil.BUFFIMG_KEY), "jpeg", sos);
            sos.close();
            //设置验证码过期时间
            VerifyCodeUtil.removeAttrbute(session);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
