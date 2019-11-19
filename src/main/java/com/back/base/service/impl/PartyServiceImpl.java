package com.back.base.service.impl;

import com.back.base.dao.EPartyMapper;
import com.back.base.model.EParty;
import com.back.base.service.PartyService;
import com.back.base.MapParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PartyServiceImpl implements PartyService {
    @Autowired(required = true)
    EPartyMapper partyMapper;

    public List<EParty> tree(EParty party) {
        List<EParty> EPartys = new ArrayList<EParty>();
        EPartys = partyMapper.query(party);
        for(EParty EParty : EPartys){
            EParty.setOpen(true);
        }
        return EPartys;
    }

    /**
     * 递归查询出所有的父节点的id
     */
    public List<String> getAllParentIds(String partyId){
        List<String> partyIds = new ArrayList<String>();
        if(StringUtils.hasText(partyId)){
            partyIds.add(partyId);
            while(null != partyMapper.queryPid(partyId)){
                partyId = partyMapper.queryPid(partyId);
                partyIds.add(partyId);
            }
        }
        return partyIds;
    }

    public List<EParty> departmentReferenceTree(EParty party) {
        List<EParty> EPartys = new ArrayList<EParty>();
        EPartys = partyMapper.query(party);
        for(EParty EParty : EPartys){
            if(EParty.getDiscriminate().equals("company")){
                EParty.setChkDisabled(false);
            }
            EParty.setOpen(true);
        }
        return EPartys;
    }

    public Map<Object, Object> getAllMap(MapParam param) {
        // TODO Auto-generated method stub
        return partyMapper.getAllMap(param);
    }

    public Map<Object, Object> getAllMap() {
        // TODO Auto-generated method stub
        return null;
    }

    public Map<Object, Object> getOtherMap(MapParam param) {
        // TODO Auto-generated method stub
        return partyMapper.getOtherMap(param);
    }

    public String getOtherMapHtml(MapParam param,String name,String id,String selVal) {
        // TODO Auto-generated method stub
        Map<Object, Object> map = partyMapper.getOtherMap(param);
        List <EParty> partys = partyMapper.getOther(new EParty());

        String str = "<select name='"+name+"' id='"+id+"'   class=\"validate[required,length[0,40]]\" >";

        str += "<option value=''>--查看所有--</option> ";
        for (EParty tpar :partys) {


            str += "<option value='"+tpar.getId()+"'  ";

            if(tpar.getId().equals(selVal)){

                str +="selected";
            }

            str += 	   ">"+tpar.getName()+"</option>";
        }
        str += "</select>";

        return str;
    }

    public String getOtherMapNameHtml(MapParam param,String name,String id,String selVal) {
        // TODO Auto-generated method stub
        Map<Object, Object> map = partyMapper.getOtherMap(param);
        List <EParty> partys = partyMapper.getOther(new EParty());

        String str = "<select name='"+name+"' id='"+id+"'   class=\"validate[required,length[0,40]]\" >";

        str += "<option value=''>--查看所有--</option> ";
        for (EParty tpar :partys) {


            str += "<option value='"+tpar.getName()+"'  ";

            if(tpar.getName().equals(selVal)){

                str +="selected";
            }

            str += 	   ">"+tpar.getName()+"</option>";
        }
        str += "</select>";

        return str;
    }

    public List<EParty> getOther(EParty party) {
        // TODO Auto-generated method stub
        return partyMapper.getOther(party);
    }

}
