package com.back.base.dao;

import com.back.base.model.ELogin;
import com.back.base.pageModel.Login;

import java.util.List;

public interface ELoginMapper extends BaseMapper<ELogin> {

    List<Login> query(Login login);

    int deleteByPrimaryKeys(String[] ids);

    Login find(Login login);

    Login selectById(String id);

    List<Login> selectByRoleId(String roleId);
}
