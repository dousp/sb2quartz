package com.dou.xin.core.exception;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 通用结果生成器
 */
public final class ApiResultGenerator {
    /**
     * 创建普通消息方法
     * @param status 状态码
     * @param msg 返回消息内容
     * @param result 返回结果
     * @param jumpUrl 跳转路径
     * @return ApiResult
     */
    public static ApiResult result(Integer status, String msg, Object result, String jumpUrl, int rows, Throwable throwable )
    {
        //创建返回对象
        ApiResult apiResult = ApiResult.newInstance();
        apiResult.setStatus(status);
        apiResult.setMsg("".equals(msg) ? "执行成功" : msg);
        apiResult.setResult(result);
        apiResult.setJumpUrl(jumpUrl);
        apiResult.setRows(rows);
        apiResult.setTime(System.currentTimeMillis());
        apiResult.setThrowable(throwable);
        return apiResult;
    }

    /**
     * 返回执行成功视图方法
     * @param result 执行成功后的返回内容
     * @return ApiResult
     */
    public static ApiResult successResult(Object result) {
        //rows默认为0
        int rows = 0;
        //如果是集合
        if(result instanceof List) {
            //获取总数量
            rows = ((List)result).size();
        }
        return result(HttpStatus.OK.value(),"",result,"",rows, null);
    }

    /**
     * 成功没有内容方法
     * @return ApiResult
     */
    public static ApiResult successResult(HttpServletRequest request){
        return successResult("");
    }

    /**
     * 执行失败后返回视图方法
     * @param msg 执行失败后的错误消息内容
     * @return ApiResult
     */
    public static ApiResult errorResult(String msg) {
        return result(HttpStatus.INTERNAL_SERVER_ERROR.value(),msg,"","",0, null);
    }

    /**
     * 执行失败后返回视图方法
     * @param throwable 执行失败后的错误消息内容
     * @return ApiResult
     */
    public static ApiResult errorResult(Throwable throwable) {
        return result(HttpStatus.INTERNAL_SERVER_ERROR.value(),throwable.getMessage(),"","",0, throwable);
    }
}
