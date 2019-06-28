package com.shf.jwt.security.jwt;

import com.alibaba.fastjson.JSON;
import com.shf.jwt.entity.properties.JwtProperties;
import com.shf.jwt.security.auth.CustomUserDetailsService;
import com.shf.jwt.utils.JwtTokenUtil;
import com.shf.jwt.utils.RedisKeys;
import com.shf.jwt.utils.RedisUtil;
import com.shf.jwt.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.shf.jwt.utils.RequestUtil.matchers;


@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        Response result = new Response();
        String authorization = request.getHeader("Authorization");
        if (matchers("/images/**", request)
                || matchers("/js/**", request)
                || matchers("/css/**", request)
                || matchers("/fonts/**", request)
                || matchers("/", request)
                || matchers("/login", request)
                || matchers("/getVerifyCode", request)
                || matchers("/auth/**", request)) {
            chain.doFilter(request, response);
            return;
        }else {
            if (StringUtils.isEmpty(authorization)) {
                response.getWriter().write(JSON.toJSONString(result.buildFailedResponse("403", "未提供token")));
                return;
            }
            if(!authorization.startsWith(jwtProperties.getTokenHead())){
                response.getWriter().write(JSON.toJSONString(result.buildFailedResponse("403", "token格式错误")));
                return;
            }
            final String token = authorization.replace(jwtProperties.getTokenHead(), "");
            String username = jwtTokenUtil.getUsernameFromToken(token);
            if(null == username){
                response.getWriter().write(JSON.toJSONString(result.buildFailedResponse("403", "无效的Token")));
                return;
            }
            if(!redisUtil.hHasKey(username, token)){
                response.getWriter().write(JSON.toJSONString(result.buildFailedResponse("403", "Token已过期")));
                return;
            }
            // 将token放到redis中，过期时间通过redis控制
//            SecurityContextHolder.getContext().getAuthentication();
//            if(jwtTokenUtil.isTokenExpired(token)){
//                response.getWriter().write(JSON.toJSONString(result.buildFailedResponse("403", "Token已过期")));
//                return;
//            }

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println(redisUtil.getExpire(username));
            //重置 token 过期时间
            redisUtil.expire(username, jwtProperties.getExpiration());
            System.out.println(redisUtil.getExpire(username));
        }
        chain.doFilter(request, response);
    }
}

/*String authHeader = request.getHeader(jwtProperties.getHeader());
        if (authHeader != null && authHeader.startsWith(jwtProperties.getTokenHead())) {
            final String authToken = authHeader.substring(jwtProperties.getTokenHead().length()); // The part after "Bearer "
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            logger.info("checking authentication " + username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    logger.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);*/