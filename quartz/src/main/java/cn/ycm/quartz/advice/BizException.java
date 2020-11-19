package cn.ycm.quartz.advice;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-19
 */
public class BizException extends RuntimeException {

    private int code;

    private String message;

    public BizException(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
