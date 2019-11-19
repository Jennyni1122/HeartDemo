package com.back.base.service.impl;

import com.back.base.dao.EPersonMapper;
import com.back.base.model.ECompany;
import com.back.base.model.EPerson;
import com.back.base.service.PersonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private static final Logger logger = Logger.getLogger(PersonServiceImpl.class);

    @Autowired(required = true)
    private EPersonMapper personMapper;

    public void save(EPerson person) {
        personMapper.insert(person);
    }


    public List<EPerson> list(EPerson person) {
        return personMapper.query(person);
    }


    public EPerson find(String id) {
        return personMapper.selectByPrimaryKey(id);
    }


    public void update(EPerson person) {
        personMapper.updateByPrimaryKey(person);
    }


    public int deleteByPrimaryKey(String id) {
        int count = personMapper.deleteByPrimaryKey(id);
        logger.info(ECompany.class.getName()+"删除成功！");
        return count;
    }


    public EPerson saveOrUpdate(EPerson person) {
        if(person.isUpdateFlag()){
            update(person);
        }else{
            save(person);
        }
        return person;
    }

}
