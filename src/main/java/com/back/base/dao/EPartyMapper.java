package com.back.base.dao;

import com.back.base.model.EParty;
import com.back.base.MapParam;

import java.util.HashMap;
import java.util.List;

public interface EPartyMapper extends BaseMapper<EParty> {
    List<EParty> queryByPid(String id);

    List<EParty> query(EParty party);

    String queryPid(String id);

    HashMap<Object,Object> getAllMap(MapParam param);

    HashMap<Object,Object> getOtherMap(MapParam param);

    List<EParty> getOther(EParty party);
}
