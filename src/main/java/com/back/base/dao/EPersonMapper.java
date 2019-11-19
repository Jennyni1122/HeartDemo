package com.back.base.dao;

import com.back.base.model.EPerson;

import java.util.List;

public interface EPersonMapper extends BaseMapper<EPerson> {
    List<EPerson> query(EPerson person);
}
