package com.back.base.controller;

import com.back.base.model.EPerson;
import com.back.base.model.EResource;
import com.back.base.pageModel.Json;
import com.back.base.service.PersonService;
import com.back.base.AbstractEntity;
import com.back.base.utils.DateUtil;
import com.back.base.utils.IConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/back/person")
public class PersonController extends BaseController {

    @Autowired(required = true)
    private PersonService personService;

    /**
     * 获取列表（可以带查询条件）
     *
     * @return
     */
    @RequestMapping(params = "cmd=list")
    public String list(EPerson person, ModelMap model) {
        List<EPerson> list = personService.list(person);
        model.put("person", person);
        model.put("list", list);
        return "backpage/base/organization/person/reference";
    }

    /**
     * 新增
     *
     * @param person
     * @return
     */
    @RequestMapping(params = "cmd=saveOrUpdate")
    public @ResponseBody
    Json saveOrUpdate(EPerson person) {
        Json json = new Json();
        if (!StringUtils.hasText(person.getId())) {
            person.setId(UUID.randomUUID().toString());
            person.setCreatetime(DateUtil.Time2String(new Date()));
            person.setUpdateFlag(false);
        } else {
            person.setUpdateFlag(true);
            person.setUpdatetime(DateUtil.Time2String(new Date()));
        }
        try {
            personService.saveOrUpdate(person);
            json.setFlag(true);
            json.setMessage(IConstant.ADD_SUCCESS);
            json.setObject(person);
        } catch (Exception e) {
            json.setFlag(false);
            json.setMessage(e.getMessage());
        }
        return json;
    }

    /**
     * 批量删除
     *ids
     * @param
     * @return
     */
    @RequestMapping(params = "cmd=delete")
    public @ResponseBody
    Json delete(String id) {
        Json json = new Json();
        try {
            if(personService.deleteByPrimaryKey(id)>0){
                json.setFlag(true);
                json.setMessage(IConstant.DELETE_SUCCESS);
            }else{
                json.setFlag(true);
                json.setMessage("未找到对应的记录！");
            }
        } catch (Exception e) {
            json.setFlag(false);
            json.setMessage(e.getMessage());
        }
        return json;
    }

    /**
     * 查询对象
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(params = "cmd=find")
    public String find(String id, ModelMap model) {
        model.put("person", personService.find(id));
        return "backpage/base/organization/person/view";
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
        model.put("person", personService.find(id));
        return "backpage/base/organization/person/edit";
    }

    /**
     * 更新
     *
     * @param person
     * @return
     */
    @RequestMapping(params = "cmd=update")
    public @ResponseBody
    Json update(EPerson person) {
        Json json = new Json();
        try{
            personService.update(person);
            json.setFlag(true);
            json.setMessage(IConstant.UPDATE_SUCCESS);
            json.setObject(person);
        }catch(Exception e){
            json.setFlag(false);
            json.setMessage(e.getMessage());
        }
        return json;
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
