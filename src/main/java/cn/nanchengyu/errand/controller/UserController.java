package cn.nanchengyu.errand.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.nanchengyu.errand.common.Result;
import cn.nanchengyu.errand.entity.User;
import cn.nanchengyu.errand.service.UserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/export")
    public void exportData(@RequestParam(required = false) String username,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String ids,
                           HttpServletResponse response) throws IOException {
        ExcelWriter writer = ExcelUtil.getWriter(true);
        List<User> list;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(ids)) {     // ["1", "2", "3"]   => [1,2,3]
            List<Integer> idsArr1 = Arrays.stream(ids.split(",")).map(Integer::valueOf).collect(Collectors.toList());
            queryWrapper.in("id", idsArr1);
        } else {
            // 第一种全部导出或者条件导出
            queryWrapper.like(StrUtil.isNotBlank(username), "username", username);
            queryWrapper.like(StrUtil.isNotBlank(name), "name", name);
        }
        list = userService.list(queryWrapper);   // 查询出当前User表的所有数据
        writer.write(list, true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("用户信息表", "UTF-8") + ".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);
        writer.close();
        outputStream.flush();
        outputStream.close();


    }

    @PostMapping("/import")
    public Result importData(MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<User> users = reader.readAll(User.class);
        //写入到数据库
        try{
            userService.saveBatch(users);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("文件导入出错");
        }

        return Result.success();
    }
}
