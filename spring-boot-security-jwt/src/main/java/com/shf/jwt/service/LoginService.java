package com.shf.jwt.service;

public interface LoginService {
    String login(String username, String password);
    String refreshToken(String token);
}
