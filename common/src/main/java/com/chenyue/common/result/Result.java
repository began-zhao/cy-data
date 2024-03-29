package com.chenyue.common.result;

import com.chenyue.common.constant.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: zzg
 * @date: 8/22/22
 * @Description: 返回实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Result<T> extends BaseResult {

    private T data;

    public Result() {
    }

    public Result(Integer code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public Result(Integer code, String errorCode, String message, T data) {
        super(code, errorCode, message);
        this.data = data;
    }
    /**
     * 未登录返回结果
     */
    public static <T> Result<T> unauthorized(T data) {
        return new Result<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> Result<T> forbidden(T data) {
        return new Result<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    public boolean success() {
        return CODE_SUCCESS.equals(getCode());
    }

    public boolean systemFail() {
        return CODE_SYSTEM_ERROR.equals(getCode());
    }

    public static Result<Object> ok() {
        return new Result<>(CODE_SUCCESS, "", null);
    }

    public static Result<Object> ok(String message) {
        return new Result<>(CODE_SUCCESS, message, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(CODE_SUCCESS, MESSAGE_SUCCESS, data);
    }

    public static <T> Result<T> success(T data, String message) {
        return new Result<>(CODE_SUCCESS, message, data);
    }

    public static Result<Object> error(String message) {
        return Result.error(CODE_SYSTEM_ERROR, null, message, null);
    }

    public static Result<Object> error(String errorCode, String message) {
        return Result.error(CODE_SYSTEM_ERROR, errorCode, message, null);
    }

    public static Result<Object> error(Integer code, String errorCode, String message, Object data) {
        return new Result<>(code, errorCode, message, data);
    }

}
