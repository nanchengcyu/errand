package cn.nanchengyu.errand.service.impl;

import cn.nanchengyu.errand.entity.Address;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.nanchengyu.errand.entity.Certification;
import cn.nanchengyu.errand.service.CertificationService;
import cn.nanchengyu.errand.mapper.CertificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author nanchengyu
* @description 针对表【certification(骑手认证)】的数据库操作Service实现
* @createDate 2024-04-26 21:30:05
*/
@Service
public class CertificationServiceImpl extends ServiceImpl<CertificationMapper, Certification>
    implements CertificationService{
    @Autowired
    CertificationMapper certificationMapper;
    @Override
    public void add(Certification certification) {
        certificationMapper.insert(certification);
    }

    @Override
    public void deleteById(Integer id) {
        certificationMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        certificationMapper.deleteBatchIds(ids);
    }

    @Override
    public Certification selectById(Integer id) {
        return certificationMapper.selectById(id);

    }

    @Override
    public List<Certification> selectAll(Certification certification) {
        return certificationMapper.selectAll(certification);
    }

    @Override
    public IPage<Certification> selectPage(Integer pageNum, Integer pageSize) {
        Page<Certification> addressPage = new Page<>(pageNum, pageSize);
        addressPage.setRecords( selectAll(null)).setTotal(selectAll(null).size()); //设置记录列表和总记录数
        return addressPage;
    }
}




