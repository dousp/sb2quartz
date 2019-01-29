package com.dou.xin.core.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.nio.file.AccessDeniedException;
import com.dou.xin.core.utils.ThrowableUtil;

@Slf4j
@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 默认统一异常处理方法
     * @param e 默认Exception异常对象
     * @return
     */
    @ExceptionHandler
    @ResponseStatus
    public ApiResult runtimeExceptionHandler(Exception e) {
        log.error(ThrowableUtil.getStackTrace(e));
        return ApiResultGenerator.errorResult(e.getMessage());
    }

    /**
     * 处理 接口无权访问异常AccessDeniedException
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResponseEntity handleAccessDeniedException(AccessDeniedException e){
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        ApiResult apiResult = ApiResultGenerator.errorResult(e.getMessage());
        return buildResponseEntity(apiResult);
    }


    /**
     * 统一返回
     * @param apiResult
     * @return
     */
    private ResponseEntity<ApiResult> buildResponseEntity(ApiResult apiResult) {
        return new ResponseEntity(apiResult, HttpStatus.valueOf(apiResult.getStatus()));
    }
}
