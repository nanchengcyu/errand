package cn.nanchengyu.errand.service.impl;

import cn.nanchengyu.errand.entity.Orders;
import cn.nanchengyu.errand.mapper.OrdersMapper;
import cn.nanchengyu.errand.service.OrdersService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nanchengyu
 * @description 针对表【orders(订单表)】的数据库操作Service实现
 * @createDate 2024-04-21 21:11:10
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
        implements OrdersService {
    @Autowired
    OrdersMapper ordersMapper;

    @Override
    public void add(Orders orders) {
        ordersMapper.insert(orders);
    }

    @Override
    public void deleteById(Integer id) {
        ordersMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        ordersMapper.deleteBatchIds(ids);
    }

    @Override
    public Orders selectById(Integer id) {
        return ordersMapper.selectById(id);
    }

    @Override
    public List<Orders> selectAll(Orders orders) {
//        return ordersMapper.selectList(null); //null表示没有特定方法的查询
       //此处涉及关联查询，改为自定义的mapper方法
       return ordersMapper.selectAll(orders);
    }

    @Override
    public IPage<Orders> selectPage(Orders orders, Integer pageNum, Integer pageSize) {
        // 获取所有记录
        List<Orders> allOrders = selectAll(new Orders());

        // 计算总记录数
        long totalRecords = allOrders.size();

        // 计算总页数
        int totalPage = (int) Math.ceil((double) totalRecords / pageSize);

        // 确保页码不超出总页数
        if (pageNum > totalPage) {
            pageNum = totalPage;
        }

        // 计算开始索引
        int fromIndex = (pageNum - 1) * pageSize;

        // 手动分页
        List<Orders> pageOrders = allOrders.subList(fromIndex, Math.min(fromIndex + pageSize, allOrders.size()));

        // 创建一个Page实例并设置数据
        Page<Orders> page = new Page<>();
        page.setRecords(pageOrders);
        page.setCurrent(pageNum);
        page.setTotal(totalRecords);
        page.setSize(pageSize);

        return page;
    }
}




