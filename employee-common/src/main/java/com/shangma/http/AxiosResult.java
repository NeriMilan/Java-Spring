package com.shangma.http;



public class AxiosResult <T>{

    // 定义什么属性?

    // 状态码
    private int status;
    // 状态消息
    private String message;
    // 返回的数据
    private T data;


    private AxiosResult(){}

    /**
     * 给返回的对象设置数据
     */
    public static <T> AxiosResult<T> setData(AxiosStatus axiosStatus,T t){
        // 创建对象
        AxiosResult<T> axiosResult = new AxiosResult<>();
        axiosResult.setStatus(axiosStatus.getStatus());
        axiosResult.setMessage(axiosStatus.getMessage());
        axiosResult.setData(t);
        return axiosResult;
    }


    /**
     * 成功  不携带数据
     */
    public static <T> AxiosResult<T> success(){
        return setData(AxiosStatus.OK,null);
    }


    /**
     * 成功  携带数据
     */
    public static <T> AxiosResult<T> success(T t){
        return setData(AxiosStatus.OK,t);
    }

    /**
     * 成功  自定义状态码 并携带数据
     */
    public static <T> AxiosResult<T> success(AxiosStatus axiosStatus,T t){
        return setData(axiosStatus,t);
    }

    /**
     * 失败 不携带数据
     */
    public static <T> AxiosResult<T> error(){
        return setData(AxiosStatus.ERROR,null);
    }

    /**
     * 失败 自定义状态
     */
    public static <T> AxiosResult<T> error(AxiosStatus axiosStatus){
        return setData(axiosStatus,null);
    }

    /**
     * 失败 自定义状态
     */
    public static <T> AxiosResult<T> error(AxiosStatus axiosStatus,T t){
        return setData(axiosStatus,t);
    }



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
