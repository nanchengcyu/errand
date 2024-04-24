package cn.nanchengyu.errand.service.impl;

import cn.nanchengyu.errand.entity.Address;
import cn.nanchengyu.errand.mapper.AddressMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.nanchengyu.errand.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author nanchengyu
* @description 针对表【address(联系人信息)】的数据库操作Service实现
* @createDate 2024-04-24 19:12:49
*/
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
    implements AddressService{

    @Autowired
    AddressMapper addressMapper;

    @Override
    public void add(Address address) {
        addressMapper.insert(address);
    }

    @Override
    public void deleteById(Integer id) {
        addressMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        addressMapper.deleteBatchIds(ids);
    }

    @Override
    public Address selectById(Integer id) {
        return addressMapper.selectById(id);
    }

    @Override
    public List<Address> selectAll(Address address) {
//        return addressMapper.selectList(null); //null表示没有特定方法的查询
        //此处涉及关联查询，改为自定义的mapper方法
        return addressMapper.selectAll(address);
    }

    @Override
    public IPage<Address> selectPage(Integer pageNum, Integer pageSize) {
        Page<Address> addressPage = new Page<>(pageNum, pageSize);
        addressPage.setRecords( selectAll(null)).setTotal(selectAll(null).size()); //设置记录列表和总记录数
        return addressPage;
    }

//    @Override
//    public IPage<Address> selectPage(Address address, Integer pageNum, Integer pageSize) {
//        // 获取所有记录
//        List<Address> allAddress= selectAll(new Address());
//
//        // 计算总记录数
//        long totalRecords = allAddress.size();
//
//        // 计算总页数
//        int totalPage = (int) Math.ceil((double) totalRecords / pageSize);
//
//        // 确保页码不超出总页数
//        if (pageNum > totalPage) {
//            pageNum = totalPage;
//        }
//
//        // 计算开始索引
//        int fromIndex = (pageNum - 1) * pageSize;
//
//        // 手动分页
//        List<Address> pageAddress= allAddress.subList(fromIndex, Math.min(fromIndex + pageSize, allAddress.size()));
//
//        // 创建一个Page实例并设置数据
//        Page<Address> page = new Page<>();
//        page.setRecords(pageAddress);
//        page.setCurrent(pageNum);
//        page.setTotal(totalRecords);
//        page.setSize(pageSize);
//
//        return page;
//    }

}




