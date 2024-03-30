package cn.nanchengyu.errand.service;

import cn.nanchengyu.errand.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author nanchengyu
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-03-24 18:20:29
*/
public interface UserService extends IService<User> {


    void add(User user);

    void deleteById(Integer id);

    void deleteBatch(List<Integer> ids);


    List<User> selectAll(User user);

    User selectById(Integer id);

    IPage<User> selectPage(Integer pageNum, Integer pageSize);

    User login(User user);

    void register(User user);
}
