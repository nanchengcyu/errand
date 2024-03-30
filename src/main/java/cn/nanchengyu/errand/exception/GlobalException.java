package cn.nanchengyu.errand.exception;

import cn.nanchengyu.errand.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: GlobalException
 * Package: cn.nanchengyu.errand.exception
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/30 22:25
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result serviceException(ServiceException e) {

        return Result.error(e.getCode(), e.getMessage());
    }

}
