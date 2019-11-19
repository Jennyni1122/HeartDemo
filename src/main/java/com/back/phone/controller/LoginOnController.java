package com.back.phone.controller;

import com.back.base.controller.BaseController;
import com.back.base.model.EResource;
import com.back.base.pageModel.Login;
import com.back.base.service.LoginService;
import com.back.base.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LoginOnController extends BaseController {


    @Override
    public String getOperateColumn(List<EResource> re, AbstractEntity ae) {
        return null;
    }

    @Override
    public String getOperateButton(List<EResource> re, String[] params) {
        return null;
    }

    @Autowired(required = true)
    private LoginService loginService;









    @RequestMapping(value = "/phone/logon_on")
    @ResponseBody
    public Login loginOn(Login login, ModelMap model){
        login = loginService.login(login);
        if (null != login){
            return login;
        }else {
            Login ll = new Login();
            return ll;

        }


    }


}
