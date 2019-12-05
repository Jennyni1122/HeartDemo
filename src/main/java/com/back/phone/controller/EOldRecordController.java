package com.back.phone.controller;


import com.back.base.AbstractEntity;
import com.back.base.controller.BaseController;
import com.back.base.model.EResource;
import com.back.phone.service.EOldRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EOldRecordController extends BaseController {
    @Override
    public String getOperateColumn(List<EResource> re, AbstractEntity ae) {
        return null;
    }

    @Override
    public String getOperateButton(List<EResource> re, String[] params) {
        return null;
    }

    @Autowired(required = true)
    private EOldRecordService OldRecordService;


}
