package cn.ycm.quartz.advice;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-19
 */
public class BizResult<T> {
    /**
     * 业务状态码
     */
    private int bizCode;
    /**
     * 业务信息
     */
    private String msg;
    /**
     * 业务结果
     */
    private T data;

    public BizResult() {
        this.bizCode = 1;
        this.msg = "";
    }

    public BizResult(T data) {
        this.bizCode = 1;
        this.msg = "";
        this.data = data;
    }

    public BizResult(int bizCode, String msg) {
        this.bizCode = bizCode;
        this.msg = msg;
    }

    public BizResult(int bizCode, String msg, T data) {
        this.bizCode = bizCode;
        this.msg = msg;
        this.data = data;
    }

    public int getBizCode() {
        return bizCode;
    }

    public void setBizCode(int bizCode) {
        this.bizCode = bizCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
