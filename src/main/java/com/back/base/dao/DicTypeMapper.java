package com.back.base.dao;

import com.back.base.model.DicType;

import java.util.List;

public interface DicTypeMapper extends BaseMapper<DicType> {
    List<DicType> selectAll(DicType dicType);
}
