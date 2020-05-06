package com.mike.model;

public class HelloDto {
    String msg = null;
    long number = 0L;

    public HelloDto(String msg, long number) {
        this.msg = msg;
        this.number = number;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}
