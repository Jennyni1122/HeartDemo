package com.back.phone.service;

import com.back.phone.model.EDoctor;

import java.util.List;

public interface EDoctorService {
    
    int deleteByPrimaryKey(String doctorId);

    int insert(EDoctor record);

    int insertSelective(EDoctor record);

    EDoctor selectByPrimaryKey(String doctorId);

    int updateByPrimaryKeySelective(EDoctor record);

    int updateByPrimaryKey(EDoctor record);

    List<EDoctor> queryTempList(EDoctor record);
}
