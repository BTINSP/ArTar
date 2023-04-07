package com.artar.accountManageService.component;

import lombok.Data;


@Data
public class Result {
    private Integer code;
    private String msg;

    private Object data;

    public static Result success(Object data){
        return success(200,"success", data);
    }

    public static Result fail(Object data){
        return fail(400,"fail",data);
    }

    public static Result success(Integer code, String msg,Object data){
        Result result = new Result();
        result.setCode(code);;
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
    public static Result fail(Integer code, String msg,Object data){
        Result result = new Result();
        result.setCode(code);;
        result.setMsg(msg);
        result.setData(data);
        return result;
    }


}
