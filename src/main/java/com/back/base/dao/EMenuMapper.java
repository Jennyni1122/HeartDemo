package com.back.base.dao;

import com.back.base.model.EMenu;

import java.util.List;

public interface EMenuMapper extends BaseMapper<EMenu> {
    
    List<EMenu> selectRoot();

    List<EMenu> selectByPid(String pid);

    int deleteAll();

    List<EMenu> selectAll();
}
