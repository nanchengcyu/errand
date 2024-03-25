package cn.nanchengyu.errand.exception;

import cn.nanchengyu.errand.common.ErrorCodeEnum;

public class CustomException extends RuntimeException {
    private ErrorCodeEnum errorCode;

    public CustomException(ErrorCodeEnum errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CustomException(ErrorCodeEnum errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

    public ErrorCodeEnum getErrorCode() {
        return errorCode;
    }
}
