package com.back.base.service.impl;

import com.back.base.dao.EMenuMapper;
import com.back.base.dao.EResourceMapper;
import com.back.base.model.EMenu;
import com.back.base.model.EResource;
import com.back.base.service.ResourceService;
import com.back.base.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ResourceServiceImpl implements ResourceService {
    private static final Logger logger = Logger.getLogger(ResourceServiceImpl.class);

    @Autowired(required = true)
    private EMenuMapper menuMapper;

    @Autowired(required = true)
    private EResourceMapper resourceMapper;

    public EResource find(String id) {
        return resourceMapper.selectByPrimaryKey(id);
    }

    public List<EResource> list(EResource resource) {
        return resourceMapper.query(resource);
    }

    public EResource saveOrUpdate(EResource resource) {
        if (resource.isUpdateFlag()) {
            resourceMapper.updateByPrimaryKeySelective(resource);
        } else {
            EResource parenEResource = resourceMapper.queryRoot(resource.getMenuid());// 父节点
            if (null == parenEResource) {// 无父节点，先插入父节点
                EMenu menu = menuMapper.selectByPrimaryKey(resource.getMenuid());
                parenEResource = new EResource();
                parenEResource.setId(UUID.randomUUID().toString());
                parenEResource.setMenuid(resource.getMenuid());
                parenEResource.setName(menu.getName());
                parenEResource.setCreatetime(DateUtil.Time2String(new Date()));
                resourceMapper.insertSelective(parenEResource);
                resource.setPid(parenEResource.getId());
            } else {
                resource.setPid(parenEResource.getId());
                resourceMapper.insertSelective(resource);
            }
        }
        return resource;
    }

    public int delete(String id) {
        String pid = resourceMapper.selectByPrimaryKey(id).getPid();
        int count = resourceMapper.deleteByPrimaryKey(id);
        if (resourceMapper.selectSiblingsCount(id) == 0) {// 没有同级节点，删除父节点
            count += resourceMapper.deleteByPrimaryKey(pid);
        }
        logger.info(EResource.class.getName() + "数据删除成功！");
        return count;
    }
    
    
}
