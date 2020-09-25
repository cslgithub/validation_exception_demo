package com.csl.demo.config;

import com.csl.demo.enums.ResultCode;
import com.csl.demo.vo.ResultVO;
import com.csl.demo.annotation.ExceptionCode;
import com.csl.demo.exception.APIException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;

/**
 * @author RudeCrab
 * @description 全局异常处理
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(APIException.class)
    public ResultVO<String> APIExceptionHandler(APIException e) {
        return new ResultVO<>(ResultCode.FAILED, e.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) throws NoSuchFieldException {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 从异常对象中拿到错误消息
        String defaultMessage=objectError.getDefaultMessage();

        // 参数的Class对象，等下好通过字段名称获取Field对象
        Class<?> paramType=e.getParameter().getParameterType();
        //
        String fieldName=e.getBindingResult().getFieldError().getField();
        Field field=paramType.getDeclaredField(fieldName);

        //获取Field对象上的自定义注解
        ExceptionCode annotation=field.getAnnotation(ExceptionCode.class);
        // 有注解的话就返回注解的响应信息
        if (annotation!=null){
            return new ResultVO<>(annotation.value(),annotation.message(),defaultMessage);
        }
        //没有注解就提取错误提示信息进行返回统一错误码
        return new ResultVO<>(ResultCode.VALIDATE_FAILED,defaultMessage);
        // 然后提取错误提示信息进行返回
//        return new ResultVO<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage());
    }

}
