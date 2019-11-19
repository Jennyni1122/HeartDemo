package com.back.base.dao;

import com.back.base.model.Eroletask;

import java.util.List;

public interface EroletaskMapper {

    int insert(Eroletask record);

    int deleteByPrimaryKeys(String[] ids);


    int deleteByPrimaryKey(String id);


    List<Eroletask> selectAll(Eroletask record);
}
