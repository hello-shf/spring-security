package com.shf.sso.server.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/7/9 11:30
 * @Version V1.0
 **/
@ConfigurationProperties(prefix = "iot.auth")
@Component
public class AuthProperties {
    /**
     * 不需要授权访问的访问地址 ‘,’号相隔，不要有空格
     */
    private String permitAll;

    /**
     * 管理平台登录地址
     */
    public String htLoginPage;
    /**
     * 审批系统登录地址
     */
    public String spLoginPage;

    public String getHtLoginPage() {
        return htLoginPage;
    }

    public void setHtLoginPage(String htLoginPage) {
        this.htLoginPage = htLoginPage;
    }

    public String getSpLoginPage() {
        return spLoginPage;
    }

    public void setSpLoginPage(String spLoginPage) {
        this.spLoginPage = spLoginPage;
    }

    public String[] getPermitAll() {
        if (null == this.permitAll || this.permitAll.length() == 0){
            return null;
        }else{
            return this.permitAll.split(",");
        }
    }

    public void setPermitAll(String permitAll) {
        this.permitAll = permitAll;
    }
}
