package com.example.tea.front.server.core.ex.handler;

import com.example.tea.front.server.common.exception.ServiceException;
import com.example.tea.front.server.common.web.JsonResult;
import com.example.tea.front.server.common.web.ServiceCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.StringJoiner;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @className GlobalExceptionHandler
 * @date 2023/06/15 11:41
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    public GlobalExceptionHandler() {
        log.info("开始创建全局异常处理器: GlobalExceptionHandler");
    }

    @ExceptionHandler(ServiceException.class)
    public JsonResult handleServiceException(ServiceException e) {
        log.debug("全局异常处理器开始处理ServiceException");
        return JsonResult.fail(e);
    }

    @ExceptionHandler(BindException.class)
    public JsonResult handleBindException(BindException e) {
        log.info("全局异常处理器开始处理BindException");

        FieldError fieldError = e.getFieldError();
        assert fieldError != null;
        StringJoiner stringJoiner = new StringJoiner("", "请求失败，", "!");
        stringJoiner.add(fieldError.getDefaultMessage());
        String message = stringJoiner.toString();
        log.warn(message);
        return JsonResult.fail(ServiceCode.ERROR_BAD_REQUEST, message);
    }

    // 在controller里的方法的参数列表里的参数上添加了@Range、@NotNull等注解后，客户端提供的参数不符合要求时的异常
    // 例如: public JsonResult delete(@PathVariable @Range(min = 1, message = "删除标签失败，请提交合法的ID值!") Long id) {
    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResult handleConstraintViolationException(ConstraintViolationException e) {
        log.info("全局异常处理器开始处理ConstraintViolationException");
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        StringBuilder builder = new StringBuilder();
        for (ConstraintViolation<?> item : constraintViolations) {
            builder.append(item.getMessage());
        }
        String message = builder.toString();
        return JsonResult.fail(ServiceCode.ERROR_BAD_REQUEST, message);
    }

    // 在UserDetailsServiceImpl的loadUserByUsername的方法返回值为null时抛出InternalAuthenticationServiceException异常
    // 在UserDetailsServiceImpl的loadUserByUsername的方法中密码比对不上时抛出BadCredentialsException
    @ExceptionHandler({InternalAuthenticationServiceException.class, BadCredentialsException.class})
    public JsonResult handlerAuthenticationException(AuthenticationException e) {
        log.info("全局异常处理器开始处理AuthenticationException");
        log.debug("异常类型为: {}", e.getClass().getName());
        String message = "登录失败，用户名或者密码错误!";
        return JsonResult.fail(ServiceCode.ERROR_UNAUTHORIZED, message);
    }

    @ExceptionHandler(DisabledException.class)
    public JsonResult handlerDisabledException(DisabledException e) {
        log.info("全局异常处理器开始处理DisabledException");
        String message = "登录失败，此账号已被禁用!";
        log.warn(message);
        return JsonResult.fail(ServiceCode.ERROR_UNAUTHORIZED_DISABLED, message);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public JsonResult handleAccessDeniedException(AccessDeniedException e) {
        log.debug("全局异常处理器开始处理AccessDeniedException");
        String message = "操作失败，当前登录用户没有此权限!";
        return JsonResult.fail(ServiceCode.ERROR_FORBIDDEN, message);
    }

    /* JWT部分的异常处理开始 */
    // JWT过期的异常: io.jsonwebtoken.ExpiredJwtException
    @ExceptionHandler(ExpiredJwtException.class)
    public JsonResult handleExpiredJwtException(ExpiredJwtException e) {
        log.debug("全局异常处理器开始处理ExpiredJwtException");
        String message = "操作失败，当前登录用户没有此权限!";
        return JsonResult.fail(ServiceCode.ERROR_FORBIDDEN, message);
    }

    // JWT格式错误的异常: io.jsonwebtoken.SignatureException
    @ExceptionHandler(SignatureException.class)
    public JsonResult handleSignatureException(SignatureException e) {
        log.debug("全局异常处理器开始处理SignatureException");
        String message = "操作失败，当前登录用户没有此权限!";
        return JsonResult.fail(ServiceCode.ERROR_FORBIDDEN, message);
    }

    // JWT解析后的(我们存储的数据(body部分解析出来后有乱码))的格式错误异常: io.jsonwebtoken.MalformedJwtException
    @ExceptionHandler(MalformedJwtException.class)
    public JsonResult handleMalformedJwtException(MalformedJwtException e) {
        log.debug("全局异常处理器开始处理MalformedJwtException");
        String message = "操作失败，当前登录用户没有此权限!";
        return JsonResult.fail(ServiceCode.ERROR_FORBIDDEN, message);
    }
    /* JWT部分的异常处理结束 */

    // 全局异常处理的兜底
    @ExceptionHandler(Throwable.class)
    public JsonResult handleThrowable(Throwable e) {
        log.info("全局异常处理器开始处理Throwable，请查看IDEA控制台异常信息，在全局异常处理器中添加对应异常处理方法!");
        log.debug("异常跟踪信息如下: ", e);

        String message = "服务器忙，请稍后再试!";
        return JsonResult.fail(ServiceCode.ERROR_UNKNOWN, message);
    }
}
