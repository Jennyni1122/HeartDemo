package com.back.base.controller;

import com.back.base.model.EAcl;
import com.back.base.model.EMenu;
import com.back.base.model.EResource;
import com.back.base.service.AclService;
import com.back.base.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/back/acl")
public class AclController extends BaseController {

    @Autowired(required = true)
    private AclService aclService;

    /**
     * 授权操作
     * @param EAcl
     * @return
     */
    @RequestMapping(params = "cmd=save")
    public @ResponseBody String save(EAcl EAcl) {
        int count = aclService.save(EAcl);
        if (count > 0) {
            return "success";
        } else {
            return "error";
        }
    }

    /**
     * 查询权限菜单
     * @param EAcl
     * @return
     */
    @RequestMapping(params = "cmd=authorizationMenu")
    public @ResponseBody
    List<EMenu> authorizationMenu(EAcl EAcl) {
        EAcl.setResourcetype("menu");
        List<EMenu> list = aclService.authorizationMenu(EAcl);
        return list;
    }


    /**
     * 查询权限资源
     * @param EAcl
     * @return
     */
    @RequestMapping(params = "cmd=authorizationResource")
    public @ResponseBody
    List<EResource> authorizationResource(EAcl EAcl) {
        EAcl.setResourcetype("resource");
        List<EResource> list = aclService.authorizationResource(EAcl);
        return list;
    }


    @Override
    public String getOperateColumn(List<EResource> re, AbstractEntity ae) {
        return null;
    }

    @Override
    public String getOperateButton(List<EResource> re, String[] params) {
        return null;
    }
}
