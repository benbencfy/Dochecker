/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.wetoband.template.util;

import com.wetoband.template.constant.ResultCode;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 */
@Data
public class R {

    // 是否成功
    private Boolean success;

    // 返回码
    private Integer code;

    // 返回消息
    private String message;

    // 返回数据
    private Map<String, Object> data = new HashMap<String, Object>();

    // 私有构造方法
    private R() {
    }

    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMsg());
        return r;
    }

    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR.getCode());
        r.setMessage(ResultCode.ERROR.getMsg());
        return r;
    }

    // 返回this 可以链式编程
    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    // 可以传入Map,或者直接一个键值对
    public R put(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R put(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}