package com.wetoband.template.constant;

public enum ResultCode {
    // 状态码
    SUCCESS(200, "请求成功"),
    ERROR(400, "请求出错");

    private int code;
    private String msg;
    ResultCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
