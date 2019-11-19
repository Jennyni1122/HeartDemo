package com.back.base.service.impl;

import com.back.base.dao.*;
import com.back.base.model.*;
import com.back.base.service.AclService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class AclServiceImpl implements AclService {

    private static final Logger logger = Logger.getLogger(AclServiceImpl.class);

    @Autowired(required = true)
    private EAclMapper aclMapper;

    @Autowired(required = true)
    private EMenuMapper menuMapper;

    @Autowired(required = true)
    private EResourceMapper resourceMapper;

    @Autowired(required = true)
    private DataDicMapper dataDicMapper;

    @Autowired(required = true)
    private EroletaskMapper troletaskMapper;

    public int save(EAcl eacl) {
        int count = 0;
        aclMapper.deleteByPrincipalIdAndResourceType(eacl);
        logger.info("删除授权成功！");
        String resourceids = eacl.getResourceid();
        for (String resourceid : resourceids.split(",")) {
            eacl.setResourceid(resourceid);
            eacl.setId(UUID.randomUUID().toString());
            count += aclMapper.insert(eacl);
        }
        logger.info("重新授权成功！");
        return count;
    }

    public List<EMenu> authorizationMenu(EAcl eacl) {
        List<EMenu> menus = menuMapper.selectAll();
        List<EAcl> acls = aclMapper.select(eacl);
        for (EMenu menu : menus) {
            if (menuMapper.selectByPid(menu.getId()).size() > 0) {// 父节点
                menu.setOpen(true);// 打开
            }
            for (EAcl acl : acls) {
                if (menu.getId().equals(acl.getResourceid())) {//有权限
                    menu.setChecked(true);//选中
                }
            }
        }
        return menus;
    }

    public List<EResource> authorizationResource(EAcl eacl) {
        List<EResource> resources = resourceMapper.select(new EResource());
        List<EAcl> acls = aclMapper.select(eacl);
        for (EResource resource : resources) {
            resource.setOpen(true);// 打开
            if(StringUtils.hasText(resource.getType())){
                if("0".equals(resource.getType())){
                    resource.setName(resource.getName()+"(列表按钮)");
                }else if("1".equals(resource.getType())){
                    resource.setName(resource.getName()+"(功能按钮)");
                }
            }
            for (EAcl acl : acls) {
                if (resource.getId().equals(acl.getResourceid())) {//有权限
                    resource.setChecked(true);//选中
                }
            }
        }
        return resources;
    }

    public List<EResource> authorizationTaskRole(EAcl eacl){

        DataDic dd = new DataDic();
        dd.setDicName("代办类型");
        List<DataDic> dateDic = dataDicMapper.selectAll(dd);
        List<EResource>resources = new ArrayList<EResource>();
        Eroletask tr = new Eroletask();
        tr.setRoleId(eacl.getPrincipalid());
        List <Eroletask> troleTask = troletaskMapper.selectAll(tr);

        eacl.getPrincipalid();

        for(DataDic rdd : dateDic){
            EResource  resource = new EResource();

            resource.setOpen(true);// 打开
            resource.setName(rdd.getDicValue());
            resource.setId(rdd.getBusCode()+"_"+rdd.getTypeId());
            for (Eroletask tra : troleTask) {
                if (tra.getFunId().equals(rdd.getBusCode())) {//有权限
                    resource.setChecked(true);//选中
                    break;
                }
            }
            resources.add(resource);

        }
        return resources;
    }

    public boolean havePermit(String principalId,String resourceId){
        EAcl query = new EAcl();
        query.setPrincipalid(principalId);
        query.setPrincipaltype("role");
        query.setResourceid(resourceId);
        query.setResourcetype("resource");
        List<EAcl> acls = aclMapper.query(query);
        if(null != acls && acls.size()>0){
            return true;
        }
        return false;
    }

}
