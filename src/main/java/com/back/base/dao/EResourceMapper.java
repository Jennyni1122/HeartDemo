package com.back.base.dao;

import java.util.List;

import com.back.base.model.EResource;

public interface EResourceMapper extends BaseMapper<EResource>{

	EResource queryRoot(String menuid);

	int selectSiblingsCount(String id);
	
	List<EResource> selectByPrincipalid(String id);
	
}