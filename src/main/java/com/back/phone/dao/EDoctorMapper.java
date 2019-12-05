package com.back.phone.dao;

import com.back.phone.model.EDoctor;

import java.util.List;
import java.util.Map;

public interface EDoctorMapper {
    
    int deleteByPrimaryKey(String doctorId);

    int insert(EDoctor record);

    int insertSelective(EDoctor record);

    EDoctor selectByPrimaryKey(String doctorId);

    int updateByPrimaryKeySelective(EDoctor record);

    int updateByPrimaryKey(EDoctor record);

    List<EDoctor> queryTempList(EDoctor record);

    int queryTempListByUser(Map<String, Object> record);

    int queryCusNo(EDoctor record);


}
