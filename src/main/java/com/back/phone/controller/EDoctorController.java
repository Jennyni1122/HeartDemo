package com.back.phone.controller;


import com.back.base.AbstractEntity;
import com.back.base.controller.BaseController;
import com.back.base.model.EResource;
import com.back.base.page.PageContext;
import com.back.phone.model.EDoctor;
import com.back.phone.modelNew.EDoctorNew;
import com.back.phone.service.EDoctorService;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EDoctorController extends BaseController {


    @Override
    public String getOperateColumn(List<EResource> re, AbstractEntity ae) {
        return null;
    }

    @Override
    public String getOperateButton(List<EResource> re, String[] params) {
        return null;
    }

    @Autowired(required = true)
    private EDoctorService eDoctorService;


    /**
     * 医护团队列表
     * @param eDoctor
     * @param model
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/phone/eDoctor_list")
    public EDoctorNew eDoctorList(EDoctor eDoctor, ModelMap model,
                                  HttpServletRequest request, HttpSession session) {

        PageContext page = PageContext.getContext(request,rowPerPage);//获取分页标签
        page.setPagination(false);

        String id = request.getParameter("id");

        String type = request.getParameter("type");


        eDoctor.setUserId(id);

        if (type !=null && type.equals("医生")){
            eDoctor.setDoctorSpare1("0");
        }

        if (type != null && type.equals("护士")){
            eDoctor.setDoctorSpare1("1");
        }

        if (type != null && type.equals("管理人员")){
            eDoctor.setDoctorSpare1("999");
        }


        List<EDoctor> eDoctors =  eDoctorService.queryTempList(eDoctor);

        EDoctorNew edn = new EDoctorNew();

        edn.getEDoctorList(eDoctors);

        return edn;

    }




}
