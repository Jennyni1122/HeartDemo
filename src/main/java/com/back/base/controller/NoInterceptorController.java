package com.back.base.controller;

import com.back.base.model.EResource;
import com.back.base.pageModel.Company;
import com.back.base.pageModel.Login;
import com.back.base.pageModel.SessionInfo;
import com.back.base.service.CompanyService;
import com.back.base.service.LoginService;
import com.back.base.AbstractEntity;
import com.back.base.utils.ConfigUtil;
import com.back.base.utils.DateUtil;
import com.back.base.utils.IConstant;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/login")
public class NoInterceptorController extends BaseController{

    @Autowired(required = true)
    private LoginService loginService;

    @Autowired(required = true)
    private CompanyService companyService;


    /**
     * 登录
     * @param login
     * @param model
     * @param verifyCode
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(params = "cmd=login")
    public String login(Login login, ModelMap model, String verifyCode, HttpSession session, HttpServletRequest request) {
        try {
            String code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);        //获取生成的验证码
            if(verifyCode!=null && code!=null){
                if(!verifyCode.toLowerCase().equals(code.toLowerCase())){
                    model.put("msg", "验证码输入错误！");
                    return "/backpage/base/login";
                }

            }else{

                model.put("msg", "请输入验证码！");
                return "/backpage/base/login";
            }

            if(null != login.getUsername() && null != login.getPassword()){
//				login.setPassword(MD5Util.md5(login.getPassword()));//MD5加密
                login.setPassword(login.getPassword());

                login = loginService.login(login);
                if (null != login) {
                    login.setLogintime(DateUtil.Time2String(new Date()));
                    login.setLoginip(request.getRemoteHost());
                    login.setLoginFlag(true);
                    loginService.update(login);
                    SessionInfo sessionInfo = new SessionInfo();
                    sessionInfo.setLogin(login);
                    sessionInfo.setResourceMap(loginService.resourceList(login.getRoleId()));
                    session.setAttribute(ConfigUtil.getSessionInfoName(), sessionInfo);

                    if(login.getServiceCode()==null || login.getServiceCode().equals("")){

                        Company company = companyService.find(login.getServiceId());

                        if(company !=null){

                            login.setServiceCode(company.getCode());
                        }
                    }

                    session.setAttribute("login", login);

                    return "redirect:/login/back/loginsucc.do";
                } else {
                    model.put("msg", "用户名,密码不正确或用户被禁用！");
                    return "/backpage/base/login";
                }
            }else{
                return "/backpage/base/login";
            }
        } catch (Exception e) {
            model.put("msg", e.getMessage());
            e.printStackTrace();
            return IConstant.ERROR_PAGE;
        }

    }

    @RequestMapping(value = "/back/loginsucc")
    public String loginSuccess() {

        return "backpage/base/index";
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
