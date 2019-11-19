package com.back.base.controller;

import com.back.base.model.EParty;
import com.back.base.model.EResource;
import com.back.base.page.PageContext;
import com.back.base.service.PartyService;
import com.back.base.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/back/party")
public class PartyController extends BaseController {

    @Autowired(required = true)
    private PartyService partyService;


    @RequestMapping(params="cmd=tree")
    public @ResponseBody
    List<EParty> tree(){
        PageContext page = PageContext.getContext(request,rowPerPage);//获得分页标签

        page.setPagination(false);//修改分页状态  是否分页
        List<EParty> list = partyService.tree(new EParty());
        return list;
    }

    /**
     * 获取列表（可以带查询条件）
     *
     * @return
     */
    @RequestMapping(params = "cmd=list")
    public String list(EParty party, ModelMap model) {
        List<EParty> list = partyService.tree(party);
        model.put("list", list);
        model.put("party", party);
        return "backpage/base/authorize/party_list";
    }


    /**
     * 部门参照树
     *
     * @return
     */
    @RequestMapping(params = "cmd=departmentReferenceTree")
    public @ResponseBody List<EParty> departmentReferenceTree(EParty party, ModelMap model) {
        List<EParty> list = partyService.departmentReferenceTree(party);
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
