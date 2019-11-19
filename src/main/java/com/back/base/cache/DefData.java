package com.back.base.cache;

import com.back.base.pageModel.Company;
import com.back.base.service.CompanyService;
import org.apache.log4j.Logger;

import java.util.List;

public class DefData {
    private static String sysDeptId;
    private static String sysDeptCode;

    private static final Logger logger = Logger.getLogger(DefData.class);

    public void init(){
        logger.info("----------初始化初始数据---------");

        CompanyService companyService = (CompanyService)SpringBeanFactoryUtils.getBean("companyService");

        List<Company> coms = companyService.list(new Company());

        if(coms.size()>0){

            Company company = coms.get(0);

            sysDeptId = company.getId();
        }




        logger.info("----------初始化初始数据完毕---------");

    }


    public static String getSysDeptCode() {
        return sysDeptCode;
    }


    public static void setSysDeptCode(String sysDeptCode) {
        DefData.sysDeptCode = sysDeptCode;
    }

    public static String getSysDeptId() {
        return sysDeptId;
    }


    public static void setSysDeptId(String sysDeptId) {
        DefData.sysDeptId = sysDeptId;
    }


}
