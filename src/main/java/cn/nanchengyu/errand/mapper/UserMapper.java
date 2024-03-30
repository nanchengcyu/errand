package cn.nanchengyu.errand.mapper;

import cn.nanchengyu.errand.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author nanchengyu
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2024-03-24 18:20:29
* @Entity cn.nanchengyu.errand.entity.User
*/
public interface UserMapper extends BaseMapper<User> {

    //添加用户

    @Override
    int insert(User user);

    List<User> getAll(User user);
    @Select("select * from errand.user where username =#{username}")
    User selectByUserName(String username);
//    insert into t_employee values (null, #{name}, #{sex}, #{age}, #{entryDate})
    @Insert("insert into user (username,password) values(#{username},#{password}) ")
    void register(String username, String password);
}




