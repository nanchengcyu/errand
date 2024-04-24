package cn.nanchengyu.errand.controller;

import cn.nanchengyu.errand.common.Result;
import cn.nanchengyu.errand.entity.Address;
import cn.nanchengyu.errand.service.AddressService;
import cn.nanchengyu.errand.service.AddressService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: AddressConroller
 * Package: cn.nanchengyu.errand.controller
 * Description:
 *
 * @Author 南城余
 * @Create 2024/4/24 19:13
 * @Version 1.0
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressService addressService;
   

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Address address) {
        addressService.add(address);
        return Result.success();
    }


    /**
     * 删除
     */

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        addressService.deleteById(id);
        return Result.success();
    }


    /**
     * 批量删除
     */

    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {

        addressService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改 todo
     */

    @PutMapping("/update")
    public Result updateById(@RequestBody Address address) {

        addressService.updateById(address);
        return Result.success();
    }

    /**
     * 查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {

        Address address = addressService.selectById(id);
        return Result.success(address);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Address address) {
        List<Address> list = addressService.selectAll(address);
        return Result.success(list);
    }


    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Address address,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        IPage<Address> page = addressService.selectPage(pageNum, pageSize);
        return Result.success(page);
    }

}
