package com.group.edu.common;

import lombok.Data;

@Data
public class R {
    private int code;
    private boolean success;
    private String msg;
    private Object data;
    public static R success() {
        R r = new R();
        r.code = 200;
        r.success = true;
        return r;
    }
    public static R fail() {
        R r = new R();
        r.code = 500;
        r.success = false;
        return r;
    }
    public static R success(Object data) {
        R r = R.success();
        r.data = data;
        return r;
    }
    public static R fail(String msg) {
        R r = R.fail();
        r.msg = msg;
        return r;
    }
}
