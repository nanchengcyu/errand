package cn.nanchengyu.errand.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.nanchengyu.errand.entity.Admin;
import cn.nanchengyu.errand.service.AdminService;
import cn.nanchengyu.errand.mapper.AdminMapper;
import org.springframework.stereotype.Service;

/**
* @author nanchengyu
* @description 针对表【admin(管理员)】的数据库操作Service实现
* @createDate 2024-03-24 18:20:29
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

}




