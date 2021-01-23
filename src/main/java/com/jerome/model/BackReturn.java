package com.jerome.model;

/**
 * 返回结果
 *
 * @author jerome
 * @date 2017/3/7 10:54
 */
public class BackReturn {

    private boolean success;
    private String msg;
    private Object obj;

    public BackReturn() {
    }

    public BackReturn(boolean success, String msg, Object obj) {
        this.success = success;
        this.msg = msg;
        this.obj = obj;
    }

    public BackReturn(boolean success, Object obj) {
        this.success = success;
        this.obj = obj;
    }

    public BackReturn(boolean success, String message) {
        this.success = success;
        this.msg = message;
    }

    public BackReturn(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
