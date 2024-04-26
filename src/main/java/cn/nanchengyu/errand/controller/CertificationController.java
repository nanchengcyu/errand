package cn.nanchengyu.errand.controller;

import cn.nanchengyu.errand.common.Result;
import cn.nanchengyu.errand.entity.Certification;
import cn.nanchengyu.errand.service.CertificationService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 骑手认证
 * @TableName certification
 */
@RestController
@RequestMapping("/certification")
public class CertificationController  {
    @Autowired
    CertificationService certificationService;


    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Certification certification) {
        certificationService.add(certification);
        return Result.success();
    }


    /**
     * 删除
     */

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        certificationService.deleteById(id);
        return Result.success();
    }


    /**
     * 批量删除
     */

    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {

        certificationService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改 todo
     */

    @PutMapping("/update")
    public Result updateById(@RequestBody Certification certification) {

        certificationService.updateById(certification);
        return Result.success();
    }

    /**
     * 查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {

        Certification certification = certificationService.selectById(id);
        return Result.success(certification);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Certification certification) {
        List<Certification> list = certificationService.selectAll(certification);
        return Result.success(list);
    }


    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Certification certification,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        IPage<Certification> page = certificationService.selectPage(pageNum, pageSize);
        return Result.success(page);
    }
}