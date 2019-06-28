# spring-security
### spring-boot-security
    springboot集成spring-security实现基于验证码的登录认证。
### spring-boot-security-role
    在spring-boot-security的基础上实现角色管理。
### spring-boot-security-jwt
    实现动态角色管理，并集成JWT以token代替session。此版本未实现验证码登录的功能。
    **TIPS**：登录认证方式和spring-boot-security有所不同。该版本登录认证采用自定义的/auth/login方式替代security默认的
    /login（当然这个也是可以指定的）登录地址，简单来说也就是在自己的controller中实现了登录方法。这样做的目的在于可以在
    controller中生成token并返回。
### spring-boot-security-jwt-redis
    SpringBoot整合security实现基于验证码的登录认证和动态角色管理，在此基础上集成JWT采用token代替session，
    并将token存储到redis。
    **TIPS**：
    （1）本工程和spring-boot-security-jwt登录认证方式完全不同，上面有说spring-boot-security-jwt中采用自定义的
    controller实现登录并将token返回给前端。但是在本工程中我们采用security默认的的登录地址/login，然后在登录成功回调函数
    中拿到HttpResponse，然后生成token返回给前端，和spring-boot-security-jwt的解决方案完全不一样。
    （2）本工程采用redis存储token，将JWT的过期时间交给redis控制，从而实现token过期时间的叠加更新（我也不知道有没有专业
    的名词，简单来说是这样的，我们要实现的效果是，当用户登录后，每次访问后台我们都更新有效时间，也就是说，当用户不间断
    访问的时候，token就不会过期）。大家都知道，在JWT中更新token的有效时间，就意味着会新生成一个token，这不是我们想要的。
    我们要的效果是token不变只更新有效时间，所以这时可以采用redis的key有效时间来代替JWT原生的有效时间控制。是不是很酷。
