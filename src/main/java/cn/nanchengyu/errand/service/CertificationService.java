package cn.nanchengyu.errand.service;

import cn.nanchengyu.errand.entity.Certification;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author nanchengyu
* @description 针对表【certification(骑手认证)】的数据库操作Service
* @createDate 2024-04-26 21:30:05
*/
public interface CertificationService extends IService<Certification> {

    void add(Certification certification);

    void deleteById(Integer id);

    void deleteBatch(List<Integer> ids);

    Certification selectById(Integer id);

    List<Certification> selectAll(Certification certification);

    IPage<Certification> selectPage(Integer pageNum, Integer pageSize);
}
