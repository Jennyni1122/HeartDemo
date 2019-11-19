package com.back.base.dao;

import com.back.base.model.ECompany;
import com.back.base.pageModel.Company;

import java.util.List;

public interface ECompanyMapper extends BaseMapper<ECompany>{

    List<Company> query(Company company);

    Company find(String id);

    Company findRoot();
}
