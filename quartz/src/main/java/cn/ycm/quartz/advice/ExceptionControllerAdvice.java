package cn.ycm.quartz.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-19
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(BizException.class)
    public Object errorHandler(BizException ex) {
        BizResult bizResult = new BizResult(ex.getCode(), ex.getMessage());
        return bizResult;
    }
}
