package com.back.base.dao;

import com.back.base.model.DataDic;

import java.util.List;

public interface DataDicMapper extends BaseMapper<DataDic> {

    List<DataDic> selectAll(DataDic dataDic);

    List<DataDic>  queryByRole(DataDic dataDic);

}
