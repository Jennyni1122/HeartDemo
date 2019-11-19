package com.back.base.dao;

import com.back.base.model.ERole;

import java.util.List;

public interface ERoleMapper extends BaseMapper<ERole> {

    List<ERole> query(ERole role);

    List<ERole> selectByLoginId(String loginId);

}
