#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${middlePackage}.handler;

import ${package}.${middlePackage}.exception.CustomException;
import ${package}.${middlePackage}.exception.CustomExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import response.Result;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * controller 异常处理
 *
 * @author
 * @date 2020/1/2 17:25
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public Result handlerImCustomException(CustomException e) {
        log.error("自定义异常:{}", e.getMessage(), e);
        return new Result().buildFail(e);
    }

    /**
     * 处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常，详情继续往下看代码
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public Result BindExceptionHandler(BindException e) {
        String message = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        log.error("请求校验异常 BindException message:{} exception:{}", message, e.getMessage(), e);
        return new Result().buildFail(message);
    }

    /**
     * 处理参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        String message = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        log.error("校验参数异常 message:{} exception:{}", message, e.getMessage(), e);
        return new Result().buildFail(message);
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handlerConstraintViolationException(ConstraintViolationException e) {
        String message =
                e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        log.error("校验参数异常 message:{} exception:{}", message, e.getMessage(), e);
        return new Result().buildFail(message);
    }

    /**
     * 其他异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result handler(Exception e) {
        log.error("系统异常", e);
        return new Result().buildFail(CustomExceptionEnum.FAIL);
    }

}
