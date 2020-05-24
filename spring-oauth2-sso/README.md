# 工程目录
    | sso-center: 认证中心
    | shf-sso-client-spring-boot-starter: sso客户端服务依赖
    | sso-client1: 测试客户端1
    | sso-client2: 测试客户端2
# 工程介绍
   # sso-center
   * 基于spring-security + oauth2技术
   * 强依赖redis，认证成功后将用户信息缓存在Redis中
    
    并在实现sso的基础上增加了权限控制，为了提高客户端角色鉴权和其它权限控制，将权限控制下放至客户端
    也就是在
    
# 快速上手
   ## 分别启动三个服务
   * sso-center
   * sso-client1
   * sso-client2
   ## 登录
   * 请求sso-center
        * url：http://localhost:10090/oauth/token?username=admin&grant_type=password&scope=all&client_id=sso_client1&client_secret=user123&password=admin
        PS：username&password对应于sys_user表中的用户信息
        其它参数对应于oauth_client_details表中定义的客户端信息
        * 返回结果示例：
           ```json
          {
               "access_token": "06a3b0aa-7154-4118-b1f0-3d8c1634d82b",//token
               "token_type": "bearer",//token头
               "refresh_token": "3d0f45f6-fcc0-4498-aeae-1b1789419052",
               "expires_in": 7199,
               "scope": "all"
           }
           ```
  ## 测试
  
  * 请求客户端1
    * url：http://localhost:10091/test
    * 请求头中加上：Authorization: Bearer 06a3b0aa-7154-4118-b1f0-3d8c1634d82b
  * 请求客户端2
    * http://localhost:10092/test
    * 请求头中加上：Authorization: Bearer 06a3b0aa-7154-4118-b1f0-3d8c1634d82b
    
  ## 登出
  * url：http://localhost:10090/oauth/logout  
  * 请求头中加上：Authorization: Bearer 06a3b0aa-7154-4118-b1f0-3d8c1634d82b