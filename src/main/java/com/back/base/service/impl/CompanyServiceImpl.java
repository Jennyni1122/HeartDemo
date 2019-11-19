package com.back.base.service.impl;

import com.back.base.dao.ECompanyMapper;
import com.back.base.dao.EPartyMapper;
import com.back.base.model.ECompany;
import com.back.base.model.EParty;
import com.back.base.pageModel.Company;
import com.back.base.service.CompanyService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
    private static final Logger logger = Logger.getLogger(CompanyServiceImpl.class);

    @Autowired(required = true)
    private ECompanyMapper companyMapper;
    @Autowired(required = true)
    private EPartyMapper partyMapper;

    public Company save(Company company) {
        EParty tparty = new EParty();
        ECompany tcompany = new ECompany();

        BeanUtils.copyProperties(company, tparty);
        BeanUtils.copyProperties(company, tcompany);

        tparty.setDiscriminate("company");
        partyMapper.insertSelective(tparty);
        companyMapper.insertSelective(tcompany);
        logger.info(ECompany.class.getName()+"新增成功！");
        return company;
    }



    public List<Company> list(Company company) {
        return companyMapper.query(company);
    }



    public Company find(String id) {
        return companyMapper.find(id);
    }


    public Company findRoot() {
        return companyMapper.findRoot();
    }




    public Company update(Company company) {
        EParty tparty = new EParty();
        ECompany tcompany = new ECompany();
        BeanUtils.copyProperties(company, tparty);
        BeanUtils.copyProperties(company, tcompany);
        companyMapper.updateByPrimaryKeySelective(tcompany);
        partyMapper.updateByPrimaryKeySelective(tparty);
        logger.info(ECompany.class.getName()+"更新成功！");
        return company;
    }


    public Company saveOrUpdate(Company company) {
        if(company.isUpdateFlag()){
            update(company);
        }else{
            save(company);
        }
        return company;
    }

    public int delete(String id) {
        int count = companyMapper.deleteByPrimaryKey(id);
        count += partyMapper.deleteByPrimaryKey(id);
        logger.info(ECompany.class.getName()+"删除成功！");
        return count;
    }
}
