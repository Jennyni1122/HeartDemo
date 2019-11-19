package com.back.base.service.impl;

import com.back.base.dao.DicTypeMapper;
import com.back.base.model.DicType;
import com.back.base.service.DicTypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("dicTypeService")
public class DicTypeServiceImpl implements DicTypeService {

    private static final Logger logger = Logger.getLogger(DicTypeServiceImpl.class);

    @Autowired(required = true)
    private DicTypeMapper dicTypeMapper;

    @Override
    public List<DicType> list(DicType dicType) {
        return dicTypeMapper.query(dicType);
    }

    @Override
    public DicType saveOrUpdate(DicType dicType) {
        if(dicType.isUpdateFlag()){
            dicTypeMapper.updateByPrimaryKeySelective(dicType);

        }else{
            dicTypeMapper.insertSelective(dicType);
        }
        logger.info(DicTypeMapper.class.getName()+"数据更新成功！");
        return dicType;
    }

    @Override
    public DicType find(String pkId) {
        return dicTypeMapper.selectByPrimaryKey(pkId);
    }

    @Override
    public int delete(String pkId) {
        int count = dicTypeMapper.deleteByPrimaryKey(pkId);
        logger.info(DicTypeMapper.class.getName()+"数据删除成功！");
        return count;
    }

    @Override
    public List<DicType> selectAll(DicType dicType) {
        return dicTypeMapper.selectAll(dicType);
    }
}
