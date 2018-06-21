package com.server.edu.userservice.common.rest;

import java.io.Serializable;

/**
 * Created by ctianlong on 2018/6/6.
 */
@SuppressWarnings("all")
public class Result<T> implements Serializable
{
    private int code;
    
    private String msg;
    
    private T data;
    
    public Result(int code, String msg)
    {
        this(code, msg, null);
    }
    
    public Result(int code, String msg, T data)
    {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    
    public Result(ResultStatus status)
    {
        this(status, null);
    }
    
    public Result(ResultStatus status, T data)
    {
        this(status.code(), status.getMsg(), data);
    }
    
    public int getCode()
    {
        return code;
    }
    
    public void setCode(int code)
    {
        this.code = code;
    }
    
    public String getMsg()
    {
        return msg;
    }
    
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    
    public T getData()
    {
        return data;
    }
    
    public void setData(T data)
    {
        this.data = data;
    }
    
    @Override
    public String toString()
    {
        return "Result{" + "code=" + code + ", msg='" + msg + '\'' + ", data="
            + data + '}';
    }
    
    public static <T> Result<T> custom(int code, String msg, T data)
    {
        return new Result(code, msg, data);
    }
    
    public static Result success()
    {
        return new Result(ResultStatus.SUCCESS);
    }
    
    public static Result success(String msg)
    {
        return new Result(ResultStatus.SUCCESS.code(), msg);
    }
    
    public static Result success(ResultStatus resultStatus)
    {
        return new Result(resultStatus);
    }
    
    public static Result success(ResultStatus resultStatus, String msg)
    {
        return new Result(resultStatus.code(), msg);
    }
    
    public static <T> Result<T> successData()
    {
        return new Result(ResultStatus.SUCCESS);
    }
    
    public static <T> Result<T> successData(T data)
    {
        return new Result(ResultStatus.SUCCESS, data);
    }
    
    public static <T> Result<T> successData(String msg, T data)
    {
        return new Result(ResultStatus.SUCCESS.code(), msg, data);
    }
    
    public static <T> Result<T> successData(ResultStatus resultStatus, T data)
    {
        return new Result(resultStatus, data);
    }
    
    public static <T> Result<T> successData(ResultStatus resultStatus,
        String msg, T data)
    {
        return new Result(resultStatus.code(), msg, data);
    }
    
    public static Result fail()
    {
        return new Result(ResultStatus.FAIL);
    }
    
    public static Result fail(String msg)
    {
        return new Result(ResultStatus.FAIL.code(), msg);
    }
    
    public static Result fail(ResultStatus resultStatus)
    {
        return new Result(resultStatus);
    }
    
    public static Result fail(ResultStatus resultStatus, String msg)
    {
        return new Result(resultStatus.code(), msg);
    }
    
    public static <T> Result<T> failData(T data)
    {
        return new Result(ResultStatus.FAIL, data);
    }
    
    public static <T> Result<T> failData(String msg, T data)
    {
        return new Result(ResultStatus.FAIL.code(), msg, data);
    }
    
    public static <T> Result<T> failData(ResultStatus resultStatus, T data)
    {
        return new Result(resultStatus, data);
    }
    
    public static <T> Result<T> failData(ResultStatus resultStatus, String msg,
        T data)
    {
        return new Result(resultStatus.code(), msg, data);
    }
    
    public static Result error()
    {
        return new Result(ResultStatus.ERROR);
    }
    
    public static Result error(String msg)
    {
        return new Result(ResultStatus.ERROR.code(), msg);
    }
    
    public static Result error(ResultStatus resultStatus)
    {
        return new Result(resultStatus);
    }
    
    public static Result error(ResultStatus resultStatus, String msg)
    {
        return new Result(resultStatus.code(), msg);
    }
    
    public static <T> Result<T> errorData(T data)
    {
        return new Result(ResultStatus.ERROR, data);
    }
    
    public static <T> Result<T> errorData(String msg, T data)
    {
        return new Result(ResultStatus.ERROR.code(), msg, data);
    }
    
    public static <T> Result<T> errorData(ResultStatus resultStatus, T data)
    {
        return new Result(resultStatus, data);
    }
    
    public static <T> Result<T> errorData(ResultStatus resultStatus, String msg,
        T data)
    {
        return new Result(resultStatus.code(), msg, data);
    }
    
}
