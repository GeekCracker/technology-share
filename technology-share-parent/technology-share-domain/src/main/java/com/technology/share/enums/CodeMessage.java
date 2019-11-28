package com.technology.share.enums;

public enum CodeMessage {

    SUCCESS(200,"操作成功!"),
    UNKNOWN(-1,"系统异常，请重试..."),

    ;

    /**状态*/
    private Integer code;

    /**信息*/
    private String message;

    private CodeMessage(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
