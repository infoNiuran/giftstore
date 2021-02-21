package com.niuran.giftstore.exception;

import com.niuran.giftstore.enume.ResponseEnum;
import com.niuran.giftstore.response.base.CodeResponse;
import com.niuran.giftstore.response.base.DataResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author MrD on 2018/7/9.
 */
@RestControllerAdvice
public class ExceptionHandle {
    /**
     * 声明需要捕获的异常类 - 写成PeopleException，就是只会捕获PeopleException异常了
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public DataResponse handle(Exception e){
        if(e instanceof CommonException){
            CommonException exception = (CommonException) e;
//            return DataResponse.error(new CodeResponse(exception.getResponseEnum().getCode(),exception.getResponseEnum().getDesc()));
            return DataResponse.error(new CodeResponse(exception.getResponseEnum().getCode(),exception.getResponseEnum().getDesc()+":"+exception.getMessage()));
        }else if(e instanceof HttpRequestMethodNotSupportedException){
            HttpRequestMethodNotSupportedException exception = (HttpRequestMethodNotSupportedException) e;
            return DataResponse.error(new CodeResponse(ResponseEnum.METHOD_NOT_ALLOW.getCode(),exception.getMessage()));
        } else {
            e.printStackTrace();
            return DataResponse.error(new CodeResponse(ResponseEnum.ERROR.getCode(),ResponseEnum.ERROR.getDesc()));
        }
    }
}
