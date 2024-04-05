package cn.nanchengyu.errand.controller;

import cn.nanchengyu.errand.common.Result;
import cn.nanchengyu.errand.entity.User;
import cn.nanchengyu.errand.service.UserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: UserController
 * Package: cn.nanchengyu.errand.controller
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/24 18:29
 * @Version 1.0
 */
@CrossOrigin(origins = {"https://techmindwave.nanchengyu.cn", "http://localhost:7000"}, allowCredentials = "true")

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }


    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }


    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {

        userService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改 todo
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody User user) {

        userService.updateById(user);
        return Result.success();
    }

    /**
     * 查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {

        User user = userService.selectById(id);
        return Result.success(user);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(User user) {
        List<User> users = userService.selectAll(user);
        return Result.success(users);
    }


    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(User user,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        IPage<User> userIPage = userService.selectPage(pageNum, pageSize);
        return Result.success(userIPage);
    }

    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String username,
                               @RequestParam String name) {
        //(long current, long size)
        Page<User> page = userService.page(new Page<>(pageNum, pageSize), new QueryWrapper<User>().like("username", username).like("name", name));



        return Result.success(page);
    }

}
