package cn.nanchengyu.errand.service;

import cn.nanchengyu.errand.entity.Orders;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author nanchengyu
* @description 针对表【orders(订单表)】的数据库操作Service
* @createDate 2024-04-21 21:11:10
*/
public interface OrdersService extends IService<Orders> {

    void add(Orders orders);

    void deleteById(Integer id);

    void deleteBatch(List<Integer> ids);

    Orders selectById(Integer id);

    List<Orders> selectAll(Orders orders);

    IPage<Orders> selectPage(Orders orders, Integer pageNum, Integer pageSize);
}
