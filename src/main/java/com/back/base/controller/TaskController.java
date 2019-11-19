package com.back.base.controller;

import com.back.base.cache.DefData;
import com.back.base.cache.DictCache;
import com.back.base.model.EResource;
import com.back.base.model.Etask;
import com.back.base.page.PageContext;
import com.back.base.service.PartyService;
import com.back.base.service.TaskService;
import com.back.base.AbstractEntity;
import com.back.base.utils.DateTime;
import com.back.base.MapParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class TaskController extends BaseController {

    @Autowired(required = true)
    private TaskService taskService;

    @Autowired(required = true)
    private PartyService partyService;


    @RequestMapping(value = "/back/task_list")
    public String geEtaskMess(Etask task , ModelMap model){

        PageContext page = PageContext.getContext(request,rowPerPage);//获得分页标签

        page.setPagination(true);//修改分页状态  是否分页

        model.put("page", page);

        //查询条件
        model.put("task",task);


        if(task.getColState()==null){
            task.setColState("1");
        }

        task.setColUid(login.getId());
        System.out.println("    "+login.getServiceId());
        task.setColDept(login.getServiceId());
        task.setCreateId(login.getId());
        task.setUpdateId("1");

        if(this.login.getServiceCode().equals(DefData.getSysDeptCode())){
            MapParam param = new MapParam("mark", "name"); //键和值
            model.put("partySel",partyService.getOtherMapHtml(param,"deptCode","deptCode",task.getDeptCode()));
        }

        List<Etask> list = taskService.queryAll(task);

        MapParam param = new MapParam("id", "name"); //键和值

        Map<Object, Object> res = partyService.getAllMap(param);

        for(Etask  stask : list){
            stask.setDeptCodeMc(res.get(stask.getDeptCode()).toString());
            DictCache.dictObj(stask);
        }

        model.put("list", list);

        return "backpage/base/welcome";

    }

    @RequestMapping(value = "/index")
    public String geEtaskMess(){

        return "backpage/base/login";
    }

    @RequestMapping(value = "/back/task_state")
    public @ResponseBody
    String taskUpdateState(String masterId){

        Etask task = new Etask();
        task.setPkId(masterId);
        task.setColState("3");
        task.setUpdateTime(DateTime.getCurDate_yyyyMMddHHmmssRe());
        taskService.taskSaveOrUpdate(task);

        return "success";
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
