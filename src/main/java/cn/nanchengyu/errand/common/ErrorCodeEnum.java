package cn.nanchengyu.errand.common;

public enum ErrorCodeEnum {
    // 通用错误
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_FOUND(404, "Resource Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    UNAUTHORIZED(401, "Unauthorized"),

    // 其他自定义错误
    INVALID_INPUT(4001, "Invalid Input"),
    RESOURCE_ALREADY_EXISTS(4002, "Resource Already Exists");
    // 其他错误码...

    private final int code;
    private final String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
