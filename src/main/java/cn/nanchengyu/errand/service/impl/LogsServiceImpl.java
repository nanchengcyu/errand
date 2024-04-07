package cn.nanchengyu.errand.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.nanchengyu.errand.entity.Logs;
import cn.nanchengyu.errand.service.LogsService;
import cn.nanchengyu.errand.mapper.LogsMapper;
import org.springframework.stereotype.Service;

/**
* @author nanchengyu
* @description 针对表【logs(系统日志)】的数据库操作Service实现
* @createDate 2024-04-07 17:31:54
*/
@Service
public class LogsServiceImpl extends ServiceImpl<LogsMapper, Logs>
    implements LogsService{

}




