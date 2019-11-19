package com.back.base.controller;

import com.back.base.model.EResource;
import com.back.base.model.ERole;
import com.back.base.page.PageContext;
import com.back.base.service.LoginService;
import com.back.base.service.RoleService;
import com.back.base.AbstractEntity;
import com.back.base.utils.DateUtil;
import com.back.base.utils.IConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/back/role")
public class RoleController extends BaseController {

    @Autowired(required = true)
    private RoleService roleService;

    @Autowired(required = true)
    private LoginService loginService;


    @RequestMapping(params = "cmd=tree")
    public @ResponseBody
    List<ERole> tree() {
        List<ERole> list = roleService.list(new ERole());
        return list;
    }

    /**
     * 主页面
     *
     * @return
     */
    @RequestMapping(params = "cmd=main")
    public String main() {
        return "backpage/base/role/main";
    }


    /**
     * 获取列表（可以带查询条件）
     *
     * @return
     */
    @RequestMapping(params = "cmd=list")
    public String list(ERole role, ModelMap model) {
        List<ERole> list = roleService.list(role);
        model.put("list", list);
        model.put("role", role);
        return "backpage/base/authorize/role_list";
    }


    /**
     * 查询对象
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(params = "cmd=find")
    public String find(String id, ModelMap model, HttpServletRequest request) {
        PageContext page = PageContext.getContext(request,rowPerPage);//获得分页标签
        page.setPagination(true);//修改分页状态  是否分页
        model.put("role", roleService.find(id));
        model.put("list", loginService.selectByRoleId(id));
        model.put("page", page);
        return "backpage/base/role/view";
    }

    /**
     * 新增
     *
     * @param role
     * @return
     */
    @RequestMapping(params = "cmd=saveOrUpdate")
    public String saveOrUpdate(ERole role,ModelMap model) {
        if (!StringUtils.hasText(role.getId())) {
            role.setId(UUID.randomUUID().toString());
            role.setCreatetime(DateUtil.Time2String(new Date()));
            role.setUpdateFlag(false);
        } else {
            role.setUpdateFlag(true);
            role.setUpdatetime(DateUtil.Time2String(new Date()));
        }
        try {
            role = roleService.saveOrUpdate(role);
            if (role.isUpdateFlag()) {
                return "redirect:/back/role.do?cmd=find&id="+role.getId()+"&flag=update";//修改
            } else {
                return "redirect:/back/role.do?cmd=find&id="+role.getId()+"&flag=add";//新增
            }
        } catch (Exception e) {
            model.put("msg", e.getMessage());
            return IConstant.ERROR_PAGE;
        }
    }

    /**
     * 更新页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(params = "cmd=updateInput")
    public String updateInput(String id, ModelMap model) {
        model.put("role", roleService.find(id));
        return "backpage/base/role/edit";
    }


    /**
     * 新增页面
     *
     * @return
     */
    @RequestMapping(params = "cmd=addInput")
    public String addInput() {
        return "backpage/base/role/edit";
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "cmd=delete")
    public String delete(String id,ModelMap model) {
        try {
            roleService.delete(id);
            model.put("msg", "删除成功！");
            model.put("flag", "remove");
            return IConstant.SUCCESS_PAGE;
        } catch (Exception e) {
            model.put("msg", e.getMessage());
            return IConstant.ERROR_PAGE;
        }
    }

    /**
     * 菜单授权页面
     *
     * @return
     */
    @RequestMapping(params = "cmd=menuAuthorizeInput")
    public String menuAuthorizeInput() {
        return "backpage/base/authorize/menu_authorize";
    }


    /**
     * 资源授权页面
     *
     * @return
     */
    @RequestMapping(params = "cmd=resourceAuthorizeInput")
    public String resourceAuthorizeInput() {
        return "backpage/base/authorize/resource_authorize";
    }


    /**
     * 代办列表授权
     *
     */
    @RequestMapping(params = "cmd=taskAuthorizeInput")
    public String taskAuthorizeInput() {
        return "backpage/base/authorize/tasktype_authorize";
    }
    /**
     *
     * @param loginId
     * @param model
     * @return
     */
    @RequestMapping(params = "cmd=relationLoginList")
    public @ResponseBody List<ERole> relationLoginList(String loginId,ModelMap model) {
        List<ERole> list = roleService.selectRelationByLoginId(loginId);
        model.put("list", list);
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
