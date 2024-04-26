package cn.nanchengyu.errand.controller;

import cn.nanchengyu.errand.common.Result;
import cn.nanchengyu.errand.entity.Address;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: IndexController
 * Package: cn.nanchengyu.errand.controller
 * Description:
 *
 * @Author 南城余
 * @Create 2024/4/27 0:24
 * @Version 1.0
 */
@RestController
public class IndexController {
    @GetMapping("/")
    public String ok() {
        return "ok";
    }
}
