package com.shf.role.security.auth;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：自定义WebAuthenticationDetails，将验证码和用户名、密码一同带入AuthenticationProvider中
 *
 * @Author shf
 * @Date 2019/4/21 16:58
 * @Version V1.0
 **/
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = 6975601077710753878L;
    private final String verifyCode;
    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        verifyCode = request.getParameter("verifyCode");
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("; VerifyCode: ").append(this.getVerifyCode());
        return sb.toString();
    }
}
