package com.back.base.service.impl;

import com.back.base.dao.EAclMapper;
import com.back.base.dao.ELoginRoleMapper;
import com.back.base.dao.EMenuMapper;
import com.back.base.dao.EPersonMapper;
import com.back.base.model.EMenu;
import com.back.base.service.MenuService;
import com.back.base.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired(required = true)
    private EMenuMapper menuMapper;

    @Autowired(required = true)
    private EAclMapper aclMapper;

    @Autowired(required = true)
    private ELoginRoleMapper loginRoleMapper;

    @Autowired(required = true)
    private EPersonMapper personMapper;

    @Autowired(required = true)
    private PartyService partyService;

    public List<EMenu> tree(String loginId,String personId) {
        List<EMenu> menus = menuMapper.selectAll();
        if("admin".equals(loginId)){
            return menus;//超级管理员返回所有菜单权限
        }
        List<EMenu> authorizationMenus = new ArrayList<EMenu>();
        
        /**TAcl tacl = new TAcl();
         tacl.setPrincipalid(loginId);
         tacl.setPrincipaltype("login");
         tacl.setResourcetype("menu");
         List<TAcl> acls = aclMapper.query(tacl);//用户权限
         */

        List<String> principalIds = new ArrayList<String>();

        //principalIds.add(loginId);//用户主体

        principalIds.addAll(loginRoleMapper.queryRoleIdByLoginId(loginId));//角色主体

        //principalIds.addAll(partyService.getAllParentIds(personMapper.selectByPrimaryKey(personId).getPositionid()));//机构主体

        List<String> resourceIds = aclMapper.selectResourceIdByPrincipalIds(principalIds);//返回拥有的权限集合

        for(EMenu menu : menus){
            for(String resourceId : resourceIds){
                if(menu.getId().equals(resourceId)){
                    authorizationMenus.add(menu);
                }
            }
        }
        return authorizationMenus;
    }

    public EMenu find(String id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    public EMenu save(EMenu menu) {
        menuMapper.insert(menu);
        return menu;
    }

    public EMenu update(EMenu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
        return menu;
    }

    public EMenu saveOrUpdate(EMenu menu) {
        if(menu.isUpdateFlag()){
            update(menu);
        }else{
            save(menu);
        }
        return menu;
    }

    public int delete(String id) {
        int count = menuMapper.deleteByPrimaryKey(id);
        return count;
    }

}
