package cn.nanchengyu.errand.service.impl;

import cn.nanchengyu.errand.common.ErrorCodeEnum;
import cn.nanchengyu.errand.entity.User;
import cn.nanchengyu.errand.exception.CustomException;
import cn.nanchengyu.errand.mapper.UserMapper;
import cn.nanchengyu.errand.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.ObjectUtil;
import java.util.List;

import static cn.nanchengyu.errand.common.ErrorCodeEnum.RESOURCE_ALREADY_EXISTS;


/**
 * @author nanchengyu
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2024-03-24 18:20:29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void add(User user) {
        User dbUser = this.selectByUsername(user.getUsername());
        if (ObjectUtil.isNotNull(dbUser)){
            throw new CustomException(ErrorCodeEnum.RESOURCE_ALREADY_EXISTS);
        }
        if (ObjectUtil.isEmpty(user.getPassword())){
            user.setPassword("123456");
        }
        user.setRole("USER");
        userMapper.insert(user);
    }

    public User selectByUsername(String username){
        User params = new User();
        params.setUsername(username);
        List<User> userList = this.selectAll(params);
        return userList.size() == 0? null : userList.get(0);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        userMapper.deleteBatchIds(ids);
    }

    @Override
    public List<User> selectAll(User user) {
        return userMapper.getAll(user);
    }


    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public IPage<User> selectPage(Integer pageNum, Integer pageSize) {
        Page<User> userPage = new Page<>(pageNum, pageSize);

        return userMapper.selectPage(userPage,null);
    }


}




