package cn.nanchengyu.errand.mapper;

import cn.nanchengyu.errand.entity.Address;
import cn.nanchengyu.errand.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author nanchengyu
* @description 针对表【address(联系人信息)】的数据库操作Mapper
* @createDate 2024-04-24 19:12:49
* @Entity cn.nanchengyu.errand.entity.Address
*/
public interface AddressMapper extends BaseMapper<Address> {
    List<Address> selectAll(Address address);

}




