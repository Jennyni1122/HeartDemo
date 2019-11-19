package com.back.base.service;

import com.back.base.model.EParty;
import com.back.base.MapParam;

import java.util.List;
import java.util.Map;

public interface PartyService {
    public List<EParty> tree(EParty party);

    public List<String> getAllParentIds(String partyId);

    public List<EParty> departmentReferenceTree(EParty party);

    public Map<Object,Object> getAllMap(MapParam param);

    public Map<Object,Object> getOtherMap(MapParam param);

    public String getOtherMapHtml(MapParam param,String name,String id,String selVal);

    public String getOtherMapNameHtml(MapParam param,String name,String id,String selVal);

    public List<EParty> getOther(EParty party);
}
