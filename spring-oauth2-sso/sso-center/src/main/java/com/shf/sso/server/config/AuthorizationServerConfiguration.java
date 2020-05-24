package com.shf.sso.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 描述：授权服务器 主要是配置客户端信息和认证信息
 *
 * @Author shf
 * @Date 2019/7/9 11:30
 * @Version V1.0
 **/
@Slf4j
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private DataSource dataSource;

    /**
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    /**
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services),告诉spring security token的生成方式。
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //指定token存储位置
                .tokenStore(tokenStore())
                // 配置JwtAccessToken转换器 TODO token增强
//                .accessTokenConverter(accessTokenConverter())
//                .tokenEnhancer(tokenEnhancerChain)
                //指定认证管理器,当你选择了资源所有者密码（password）授权类型的时候，需设置这个属性注入一个 AuthenticationManager 对象。
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

        // 配置tokenServices参数
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(endpoints.getTokenStore());
//        // 是否支持刷新
//        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
//        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
//        // 1分钟
//        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.MINUTES.toSeconds(20));
//        endpoints.tokenServices(tokenServices);

//        endpoints.exceptionTranslator(new PrexCustomWebResponseExceptionTranslator());
    }

    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     *
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

        oauthServer
                // 允许客户表单认证,不加的话/oauth/token无法访问
                .allowFormAuthenticationForClients()
                // 对于CheckEndpoint控制器[框架自带的校验]的/oauth/token端点允许所有客户端发送器请求而不会被Spring-security拦截
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 要访问/oauth/check_token必须设置为permitAll()，但这样所有人都可以访问了，设为isAuthenticated()又导致访问不了，这个问题暂时没找到解决方案
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("permitAll()");
    }
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Bean
    public TokenStore tokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }
}
//以下是采用token增强方式，将用户信息统统塞到token中，资源服务器只需要解析token即可。
// 但是存在一个很大的问题，资源服务器不能立即感知到认证服务器的登出。
// TODO token增强
    /*@Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new CustomJwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;

    }*/

/*
 * 它可以把令牌相关的数据进行编码，声明TokenStore实现
 *
 * @return
 */
    /*@Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }*/