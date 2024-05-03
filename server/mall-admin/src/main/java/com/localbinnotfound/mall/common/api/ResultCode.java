package com.localbinnotfound.mall.common.api;

/**
 * 枚举了一些常用API操作码
 *
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "SUCCESS"),
    FAILED(500, "FAILED"),
    VALIDATE_FAILED(404, "VALIDATION FAILED"),
    UNAUTHORIZED(401, "UNAUTHORIZED OR TOKEN EXPIRED"),
    FORBIDDEN(403, "PERMISSION DENIED");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
