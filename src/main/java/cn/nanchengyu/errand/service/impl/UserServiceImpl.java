package cn.nanchengyu.errand.service.impl;

import cn.nanchengyu.errand.entity.User;
import cn.nanchengyu.errand.exception.ServiceException;
import cn.nanchengyu.errand.mapper.UserMapper;
import cn.nanchengyu.errand.service.UserService;
import cn.nanchengyu.errand.utils.TokenUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.ObjectUtil;

import java.util.List;


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
        if (ObjectUtil.isNotNull(dbUser)) {
//            throw new CustomException(ErrorCodeEnum.RESOURCE_ALREADY_EXISTS);
        }
        if (ObjectUtil.isEmpty(user.getPassword())) {
            user.setPassword("123456");
        }
        user.setRole("USER");
        userMapper.insert(user);
    }

    public User selectByUsername(String username) {
        User params = new User();
        params.setUsername(username);
        List<User> userList = this.selectAll(params);
        return userList.size() == 0 ? null : userList.get(0);
    }

    @Override
    public void deleteById(Integer id) {
        User currentUser = TokenUtils.getCurrentUser();
        if (id.equals(currentUser.getId())) {
            throw new ServiceException("不能删除当前的用户");
        }
        userMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {

        User currentUser = TokenUtils.getCurrentUser();
        if (currentUser != null && currentUser.getId() != null && ids.contains(currentUser.getId())) {
            throw new ServiceException("不能删除当前的用户");
        }
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

        return userMapper.selectPage(userPage, null);
    }

    @Override
    public User login(User user) {
        User dbUser = userMapper.selectByUserName(user.getUsername());
        //数据库判断
        if (dbUser == null) {
            throw new ServiceException("账号或者密码错误");
        }
        //前端和数据库判断
        if (!user.getPassword().equals(dbUser.getPassword())) {
            throw new ServiceException("账号或者密码错误");
        }
        String userId = dbUser.getId().toString();
        //生成token
        String token = TokenUtils.createToken(userId, dbUser.getPassword());

        user.setToken(token); //数据库中没有这个字段 所以应该是实体类的token
        user.setRole(dbUser.getRole());
        user.setPhone(dbUser.getPhone());
        user.setSex(dbUser.getSex());
        user.setAvatar(dbUser.getAvatar());
        return user;
    }

    @Override
    public void register(User user) {
        User dbUser = userMapper.selectByUserName(user.getUsername());
        if (dbUser != null) {
            throw new ServiceException("用户名已存在");
        }
        user.setUsername(user.getUsername());
//        userMapper.register(user.getUsername(),user.getPassword());
        userMapper.insert(user);
    }

    @Override
    public void restPassword(User user) {
        User dbUser = userMapper.selectByUserName(user.getUsername());
        if (dbUser == null) {
            throw new ServiceException("用户不存在");
        }
        if (!user.getPhone().equals(dbUser.getPhone())) {
            throw new ServiceException("验证错误");
        }
        dbUser.setPassword("123"); //重置密码
        userMapper.updatePassword(dbUser);

    }


}




