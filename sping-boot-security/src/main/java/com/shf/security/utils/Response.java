package com.shf.security.utils;

import lombok.Data;

/**
 * 描述：
 *
 * @Author shf
 * @Description TODO
 * @Date 2019/4/16 15:03
 * @Version V1.0
 **/
@Data
public class Response {
    private String code;
    private String msg;
    private Object data;
    public Response() {
        this.code = "-200";
        this.msg = "SUCCESS";
    }
    public Response(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public Response buildSuccessResponse(){
        this.code = "-200";
        this.msg = "SUCCESS";
        return this;
    }
    public Response buildFailedResponse(){
        this.code = "-400";
        this.msg = "FAILED";
        return this;
    }
    public Response buildSuccessResponse(String msg){
        this.code = "-200";
        this.msg = msg;
        return this;
    }
    public Response buildFailedResponse(String msg){
        this.code = "-400";
        this.msg = msg;
        return this;
    }
    public Response buildFailedResponse(String code, String msg){
        this.code = code;
        this.msg = msg;
        return this;
    }
    public Response buildSuccessResponse(String code, String msg){
        this.code = code;
        this.msg =  msg;
        return this;
    }
}

