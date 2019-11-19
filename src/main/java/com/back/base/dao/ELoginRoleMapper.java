package com.back.base.dao;

import com.back.base.model.ELoginRoleKey;

import java.util.List;

public interface ELoginRoleMapper extends BaseMapper<ELoginRoleMapper> {

    int deleteByPrimaryKey(ELoginRoleKey key);

    int deleteByLoginIds(String[] ids);

    List<String> queryRoleIdByLoginId(String loginId);
}
