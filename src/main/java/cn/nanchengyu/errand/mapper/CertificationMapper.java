package cn.nanchengyu.errand.mapper;

import cn.nanchengyu.errand.entity.Certification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author nanchengyu
* @description 针对表【certification(骑手认证)】的数据库操作Mapper
* @createDate 2024-04-26 21:30:05
* @Entity cn.nanchengyu.errand.entity.Certification
*/
public interface CertificationMapper extends BaseMapper<Certification> {

    List<Certification> selectAll(Certification certification);
}




