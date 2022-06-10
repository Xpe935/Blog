package com.icarus.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
public class Result implements Serializable {
    private String code;
    private String msg;
    private Object data;


    //状态
    public static Result state(String code, Object data, String mess) {
        Result m = new Result();
        m.setCode(code);
        m.setData(data);
        m.setMsg(mess);
        return m;
    }

    //成功
    public static Result success(Object data) {
        return state("200", data, "成功");
    }


    //其他
    public static Result fail(String data) {
        return state("400", data, "失败");
    }


}
