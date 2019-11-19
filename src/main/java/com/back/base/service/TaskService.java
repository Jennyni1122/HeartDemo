package com.back.base.service;

import com.back.base.model.Etask;

import java.util.List;

public interface TaskService {
    
    public List<Etask> queryAll(Etask task);

    public int taskSaveOrUpdate(Etask task);

    public Etask find(String id);

    public int updateTaskClient(String pkId);

    public int deleteTaskAdmin(String pkId);
}
