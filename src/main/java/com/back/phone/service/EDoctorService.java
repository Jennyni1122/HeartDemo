package com.back.phone.service;

import com.back.phone.model.EDoctor;

import java.util.List;

public interface EDoctorService {
    
    int deleteByPrimaryKey(String customerId);

    int insert(EDoctor record);

    int insertSelective(EDoctor record);

    EDoctor selectByPrimaryKey(String customerId);

    int updateByPrimaryKeySelective(EDoctor record);

    int updateByPrimaryKey(EDoctor record);

    List<EDoctor> queryTempList(EDoctor record);
}
