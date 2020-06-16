package com.ithao.vueblog.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private String code;
    private String msg;
    private Object data;

    //重载一个简单的默认的方法
    public static Result succ(Object data){
       return succ("200","操作成功！",data);
    }
    //可以自己自定义操作成功的信息！
    public static Result succ(String code,String msg,Object data){
        Result m = new Result();
        m.setCode(code);
        m.setMsg(msg);
        m.setData(data);
        return m;
    }
    public static Result fail(String msg){
       return fail("400",msg,null);
    }
    public static Result fail(String msg,Object data){
        Result m = new Result();
        m.setCode("400");
        m.setMsg(msg);
        m.setData(data);
        return m;
    }

    public static Result fail(String code,String msg,Object data){
        Result m=new Result();
        m.setCode(code);
        m.setMsg(msg);
        m.setData(data);
        return  m;
    }
}
