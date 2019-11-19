package com.back.base.dao;

import com.back.base.model.Etask;

import java.util.List;

public interface EtaskMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(Etask record);

    int insertSelective(Etask record);

    Etask selectByPrimaryKey(String pkId);

    int updateByPrimaryKeySelective(Etask record);

    int updateByPrimaryKey(Etask record);

    int updateTaskClient(String pkId);

    int deleteTaskAdmin(String pkId);


    List<Etask> query(Etask task);
}
