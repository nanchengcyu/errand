package cn.nanchengyu.errand.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.nanchengyu.errand.entity.Notice;
import cn.nanchengyu.errand.service.NoticeService;
import cn.nanchengyu.errand.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

/**
* @author nanchengyu
* @description 针对表【notice(公告信息表)】的数据库操作Service实现
* @createDate 2024-03-24 18:20:29
*/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
    implements NoticeService{

}




