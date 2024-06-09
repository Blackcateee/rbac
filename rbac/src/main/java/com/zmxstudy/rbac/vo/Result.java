package com.zmxstudy.rbac.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回结果
 *
 * @author star
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    /**
     * 错误码：success：2000
     */
    private String code;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 返回数据
     */
    @JsonInclude
    private T data;

    public static <T> Result<T> build(String code, String msg, T data) {
        return new Result<T>(code, msg, data);
    }

    public static <T> Result<T> ok() {
        return build(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    public static <T> Result<T> ok(String msg) {
        return build(ResultCode.SUCCESS.getCode(), msg, null);
    }

    public static <T> Result<T> ok(T data) {
        return build(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> ok(T data, String msg) {
        return build(ResultCode.SUCCESS.getCode(), msg, data);
    }

    /**
     * 客户端错误
     */
    public static <T> Result<T> clientError() {
        return build(ResultCode.CLIENT_A0001.getCode(), ResultCode.CLIENT_A0001.getMessage(), null);
    }

    /**
     * 系统错误
     */
    public static <T> Result<T> systemError() {
        return build(ResultCode.SYSTEM_B0001.getCode(), ResultCode.SYSTEM_B0001.getMessage(), null);
    }

    /**
     * 第三方错误
     */
    public static <T> Result<T> thirdPartyError() {
        return build(ResultCode.THIRD_PARTY_C0001.getCode(), ResultCode.THIRD_PARTY_C0001.getMessage(), null);
    }

    public static <T> Result<T> error(ResultCode resultCode, String msg, T data) {
        return build(resultCode.getCode(), msg, data);
    }

    public static <T> Result<T> error(ResultCode resultCode, T data) {
        return build(resultCode.getCode(), resultCode.getMessage(), data);
    }

    public static <T> Result<T> error(ResultCode resultCode, String msg) {
        return build(resultCode.getCode(), msg, null);
    }

    public static <T> Result<T> error(ResultCode resultCode) {
        return build(resultCode.getCode(), resultCode.getMessage(), null);
    }
}
