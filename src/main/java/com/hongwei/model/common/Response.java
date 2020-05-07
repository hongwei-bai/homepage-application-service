package com.hongwei.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public Response() {
    }

    public Response(int code, String msg, T data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static Response from(int code) {
        return new Response(code, null, null);
    }

    public static Response from(int code, String msg) {
        return new Response(code, msg, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseDto{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
