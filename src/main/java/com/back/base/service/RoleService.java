package com.back.base.service;

import com.back.base.model.ERole;

import java.util.List;

public interface RoleService {

    public ERole find(String id);

    public ERole save(ERole role);

    public ERole update(ERole role);

    public List<ERole> list(ERole role);

    public ERole saveOrUpdate(ERole role);

    public int delete(String id);

    public List<ERole> selectRelationByLoginId(String loginId);
}
