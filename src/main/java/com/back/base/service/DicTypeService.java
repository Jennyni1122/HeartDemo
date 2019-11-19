package com.back.base.service;

import com.back.base.model.DicType;

import java.util.List;

public interface DicTypeService {

    public List<DicType> list(DicType dicType);

    public DicType saveOrUpdate(DicType dicType);

    public DicType find(String pkId);

    public int delete(String pkId);

    public List<DicType> selectAll(DicType dicType);
}
