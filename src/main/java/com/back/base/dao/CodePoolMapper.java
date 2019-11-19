package com.back.base.dao;

import com.back.base.model.CodePoolVo;
import com.back.base.model.DataDic;

import java.util.List;

public interface CodePoolMapper extends BaseMapper<DataDic>{

    int deleteCodePool(String code_value);

    int updateRelease(CodePoolVo codePoolVo);

    void insertCodePool(CodePoolVo vo);

    List findCodePool(String key);

    List<String> findMaxCode(CodePoolVo codePoolVo);


}
