package com.back.base.service.impl;

import com.back.base.dao.*;
import com.back.base.model.ELogin;
import com.back.base.model.ELoginRoleKey;
import com.back.base.model.EPerson;
import com.back.base.model.EResource;
import com.back.base.pageModel.Login;
import com.back.base.service.LoginService;
import com.back.base.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = Logger.getLogger(LoginServiceImpl.class);

    @Autowired(required = true)
    private ELoginMapper loginMapper;

    @Autowired(required = true)
    private ELoginRoleMapper loginRoleMapper;

    @Autowired(required = true)
    private EPersonMapper personMapper;

    @Autowired(required = true)
    private EResourceMapper resourceMapper;

    @Autowired(required = true)
    private EAclMapper aclMapper;

    @Override
    public void save(Login login) {
        ELogin elogin = new ELogin();
        EPerson eperson = new EPerson();
        BeanUtils.copyProperties(login, elogin);
        BeanUtils.copyProperties(login, eperson);
        eperson.setDepartmentid(login.getServiceId());
        personMapper.insertSelective(eperson);
        loginMapper.insertSelective(elogin);
        logger.info(ELogin.class.getName() + "新增成功！");
    }

    @Override
    public List<Login> list(Login login) {
        return loginMapper.query(login);
    }

    @Override
    public Login find(String id) {
        return loginMapper.selectById(id);
    }

    @Override
    public void update(Login login) {
        ELogin elogin = new ELogin();
        EPerson eperson = new EPerson();
        BeanUtils.copyProperties(login, elogin);
        if (!login.isLoginFlag()) {
            BeanUtils.copyProperties(login, eperson);
            eperson.setDepartmentid(login.getServiceId());
            personMapper.updateByPrimaryKeySelective(eperson);
        }
        loginMapper.updateByPrimaryKeySelective(elogin);
        logger.info(ELogin.class.getName() + "更新成功！");

    }

    @Override
    public void deleteByPrimaryKeys(String ids) {
        loginMapper.deleteByPrimaryKeys(ids.split(","));
        logger.info(ELogin.class.getName() + "批量删除成功！");

    }

    @Override
    public Login login(Login login) {
        return loginMapper.find(login);
    }

    @Override
    public void relationLogin(String roleIds, String loginIds) {
        loginRoleMapper.deleteByLoginIds(loginIds.split(","));
        logger.info(ELogin.class.getName() + "删除关联成功！");
        for (String loginId : loginIds.split(",")) {
            ELoginRoleKey loginRole = new ELoginRoleKey();
            loginRole.setLoginid(loginId);
            for (String roleId : roleIds.split(",")) {
                loginRole.setRoleid(roleId);
                loginRoleMapper.insert((ELoginRoleMapper) loginRole);
            }
        }
        logger.info(ELogin.class.getName() + "关联成功！");
    }

    @Override
    public List<Login> selectByRoleId(String roleId) {
        return null;
    }

    @Override
    public List<Login> selectNoRelationByRoleId(Login login, String roleId) {
        return null;
    }

    @Override
    public void deleteRelation(String ids) {
        loginRoleMapper.deleteByLoginIds(ids.split(","));
    }

    @Override
    public Login saveOrUpdate(Login login) {
        if (login.isUpdateFlag()){
            update(login);
        }else {
            save(login);
        }
        return login;
    }

    public void statusChange(String id) {
        ELogin tlogin = loginMapper.selectByPrimaryKey(id);
        if (tlogin.getEnablestate().equals("1")) {
            tlogin.setEnablestate("0");// 禁用
        } else if (tlogin.getEnablestate().equals("0")) {
            tlogin.setEnablestate("1");// 启用
            tlogin.setEnabletime(DateUtil.Time2String(new Date()));
        }
        loginMapper.updateByPrimaryKeySelective(tlogin);

        logger.info(ELogin.class.getName() + "更新成功！");
    }

    public void pwdChange(String id,String pwd) {
        ELogin elogin = loginMapper.selectByPrimaryKey(id);
        elogin.setPassword(pwd);
        loginMapper.updateByPrimaryKeySelective(elogin);

        logger.info(ELogin.class.getName() + "更新成功！");
    }

    /**
     * 根据roleId获取资源权限列表
     *
     * @param roleId
     * @return
     */
    @Override
    public Map resourceList(String roleId) {
        Map<String, List> resourceMap;
        resourceMap = new HashMap<String, List>();

        List<EResource> allReource = resourceMapper.selectByPrincipalid(roleId);
        for(EResource tr :allReource){

            if(resourceMap.get(tr.getMenuid())==null){
                List childRs = new ArrayList();
                List addButton = new ArrayList();
                List trButton = new ArrayList();
                if(tr.getType().equals("1")){
                    addButton.add(tr);
                }else if(tr.getType().equals("0")){
                    trButton.add(tr);
                }
                childRs.add(addButton);
                childRs.add(trButton);
                resourceMap.put(tr.getMenuid(), childRs);
            }else{
                List childRs =  new ArrayList();
                List addButton = (List) resourceMap.get(tr.getMenuid()).get(0);
                List trButton = (List) resourceMap.get(tr.getMenuid()).get(1);
                if(tr.getType().equals("1")){
                    addButton.add(tr);
                }else if(tr.getType().equals("0")){
                    trButton.add(tr);
                }
                childRs.add(addButton);
                childRs.add(trButton);
                resourceMap.remove(tr.getMenuid());
                resourceMap.put(tr.getMenuid(), childRs);
            }
        }
        return resourceMap;
    }
}
