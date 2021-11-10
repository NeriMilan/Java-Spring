package com.shangma.http;

public enum AxiosStatus {
    OK(20000,"操作成功"),
    ERROR(50000,"操作失败")
    ;


    // 状态码
    private int status;

    // 状态信息
    private String message;

    AxiosStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
