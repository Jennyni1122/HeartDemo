package com.back.base.service;

import com.back.base.model.EPerson;

import java.util.List;

public interface PersonService {

    public void save(EPerson person);

    public List<EPerson> list(EPerson person);

    public EPerson find(String id);

    public void update(EPerson person);

    public int deleteByPrimaryKey(String id);

    public EPerson saveOrUpdate(EPerson person);
}
