package com.back.base.service.impl;

import com.back.base.dao.ERoleMapper;
import com.back.base.model.ERole;
import com.back.base.service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = Logger.getLogger(RoleServiceImpl.class);

    @Autowired(required = true)
    private ERoleMapper roleMapper;

    @Override
    public ERole find(String id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public ERole save(ERole role) {
        roleMapper.insert(role);
        logger.info(ERole.class.getName() + "数据插入成功！");
        return role;
    }

    @Override
    public ERole update(ERole role) {
        roleMapper.updateByPrimaryKeySelective(role);
        logger.info(ERole.class.getName() + "数据更新成功！");
        return role;
    }

    @Override
    public List<ERole> list(ERole role) {
        return roleMapper.query(role);
    }

    @Override
    public ERole saveOrUpdate(ERole role) {
        if (role.isUpdateFlag()) {
            update(role);
        } else {
            save(role);
        }
        return role;
    }

    @Override
    public int delete(String id) {
        int count = roleMapper.deleteByPrimaryKey(id);
        logger.info(ERole.class.getName() + "数据删除成功！");
        return count;
    }

    @Override
    public List<ERole> selectRelationByLoginId(String loginId) {
        List<ERole> roles = roleMapper.query(new ERole());
        List<ERole> selectedRoles = roleMapper.selectByLoginId(loginId);

        for(ERole role : roles){
            role.setOpen(true);
            for(ERole selectedRole : selectedRoles){
                if(role.getId().equals(selectedRole.getId())){
                    role.setChecked(true);
                }
            }
        }
        return roles;
    }
}
