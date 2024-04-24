package cn.nanchengyu.errand.service;

import cn.nanchengyu.errand.entity.Address;
import cn.nanchengyu.errand.entity.Address;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author nanchengyu
* @description 针对表【address(联系人信息)】的数据库操作Service
* @createDate 2024-04-24 19:12:49
*/
public interface AddressService extends IService<Address> {
    void add(Address address);

    void deleteById(Integer id);

    void deleteBatch(List<Integer> ids);

    Address selectById(Integer id);

    List<Address> selectAll(Address address);


    IPage<Address> selectPage(Integer pageNum, Integer pageSize);
}
