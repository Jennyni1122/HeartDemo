package com.back.phone.service.service.impl;

import com.back.phone.dao.EDoctorMapper;
import com.back.phone.model.EDoctor;
import com.back.phone.service.EDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EDoctorServiceImpl implements EDoctorService {

    @Autowired(required = true)
    private EDoctorMapper eDoctorMapper;

    @Override
    public int deleteByPrimaryKey(String doctorId) {
        return eDoctorMapper.deleteByPrimaryKey(doctorId);
    }

    @Override
    public int insert(EDoctor record) {
        if (record.getDoctorId() == null || record.getDoctorId().equals("")){
            record.setDoctorId(UUID.randomUUID().toString());
        }
        return eDoctorMapper.insert(record);
    }

    @Override
    public int insertSelective(EDoctor record) {
        return eDoctorMapper.insertSelective(record);
    }

    @Override
    public EDoctor selectByPrimaryKey(String doctorId) {
        return eDoctorMapper.selectByPrimaryKey(doctorId);
    }

    @Override
    public int updateByPrimaryKeySelective(EDoctor record) {
        return eDoctorMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(EDoctor record) {
        return eDoctorMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<EDoctor> queryTempList(EDoctor record) {
        return eDoctorMapper.queryTempList(record);
    }
}
