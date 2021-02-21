package com.zongce.gateway.resultCode;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data

public class ResultUtils {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String,Object> data=new HashMap<String,Object>();

    private ResultUtils(){}//对象私有化，只能用这个
    public static ResultUtils OK(){
            ResultUtils r=new ResultUtils() ;
            r.setCode(ResultCode.SUCCESS);
            r.setMessage("成功");
            r.setSuccess(true);
          return r;
    }
    public static ResultUtils error(){
        ResultUtils r=new ResultUtils() ;
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        r.setSuccess(false);
        return r;
    }
    public ResultUtils success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public ResultUtils message(String message){
        this.setMessage(message);
        return this;
    }

    public ResultUtils code(Integer code){
        this.setCode(code);
        return this;
    }

    public ResultUtils data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public ResultUtils data(Map<String, Object> map){
        this.setData(map);
        return this;

    }
}
