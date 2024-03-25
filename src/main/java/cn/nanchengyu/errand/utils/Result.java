package cn.nanchengyu.errand.utils;

public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Getters and setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // Convenience methods for common responses
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "Success", data);
    }

    public static <T> Result<T> success() {
        return new Result<>(200,"success",null);
    }

    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message, null);
    }
}
