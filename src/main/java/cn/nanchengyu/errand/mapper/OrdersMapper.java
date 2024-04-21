package cn.nanchengyu.errand.mapper;

import cn.nanchengyu.errand.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author nanchengyu
* @description 针对表【orders(订单表)】的数据库操作Mapper
* @createDate 2024-04-21 21:11:10
* @Entity cn.nanchengyu.errand.entity.Orders
*/
public interface OrdersMapper extends BaseMapper<Orders> {

    List<Orders> selectAll(Orders orders);
}




