package cn.nanchengyu.errand.exception;

import lombok.Getter;

/**
 * ClassName: ServiceException
 * Package: cn.nanchengyu.errand.exception
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/30 22:26
 * @Version 1.0
 */
@Getter
public class ServiceException extends RuntimeException {
    private final String code;
    public ServiceException(String msg){
        super(msg);
        this.code = "500";
    }
    public ServiceException(String msg,String code){
        super(msg);
        this.code = code;
    }
}
