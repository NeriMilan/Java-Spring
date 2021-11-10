package com.shangma.exception;

import com.shangma.http.AxiosStatus;

public class ApiException extends RuntimeException{

    // 异常的状态码
    private AxiosStatus axiosStatus;

    public AxiosStatus getAxiosStatus() {
        return axiosStatus;
    }

    public void setAxiosStatus(AxiosStatus axiosStatus) {
        this.axiosStatus = axiosStatus;
    }

    public ApiException(AxiosStatus axiosStatus){
        this.axiosStatus = axiosStatus;
    }
}
