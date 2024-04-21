package cn.nanchengyu.errand.controller;

import cn.nanchengyu.errand.common.Result;
import cn.nanchengyu.errand.entity.Orders;
import cn.nanchengyu.errand.service.OrdersService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: OrdersController
 * Package: cn.nanchengyu.errand.controller
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/24 18:29
 * @Version 1.0
 */
@CrossOrigin(origins = {"https://techmindwave.nanchengyu.cn", "http://localhost:7000"}, allowCredentials = "true")

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    OrdersService ordersService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Orders orders) {
        ordersService.add(orders);
        return Result.success();
    }


    /**
     * 删除
     */

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        ordersService.deleteById(id);
        return Result.success();
    }


    /**
     * 批量删除
     */

    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {

        ordersService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改 todo
     */

    @PutMapping("/update")
    public Result updateById(@RequestBody Orders orders) {

        ordersService.updateById(orders);
        return Result.success();
    }

    /**
     * 查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {

        Orders orders = ordersService.selectById(id);
        return Result.success(orders);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Orders orders) {
        List<Orders> list = ordersService.selectAll(orders);
        return Result.success(list);
    }


    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Orders orders,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        IPage<Orders> page = ordersService.selectPage(orders,pageNum, pageSize);
        return Result.success(page);
    }



}
