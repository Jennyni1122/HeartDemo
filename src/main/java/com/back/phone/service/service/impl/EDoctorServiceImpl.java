package com.back.phone.service.service.impl;

import com.back.phone.model.EDoctor;
import com.back.phone.service.EDoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EDoctorServiceImpl implements EDoctorService {



    @Override
    public int deleteByPrimaryKey(String customerId) {
        return 0;
    }

    @Override
    public int insert(EDoctor record) {
        return 0;
    }

    @Override
    public int insertSelective(EDoctor record) {
        return 0;
    }

    @Override
    public EDoctor selectByPrimaryKey(String customerId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(EDoctor record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(EDoctor record) {
        return 0;
    }

    @Override
    public List<EDoctor> queryTempList(EDoctor record) {
        return null;
    }
}
