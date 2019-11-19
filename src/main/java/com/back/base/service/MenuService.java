package com.back.base.service;

import com.back.base.model.EMenu;

import java.util.List;

public interface MenuService {
    
    public List<EMenu> tree(String loginId, String personId);

    public EMenu find(String id);

    public EMenu save(EMenu menu);

    public EMenu update(EMenu menu);

    public EMenu saveOrUpdate(EMenu menu);

    public int delete(String id);

}
