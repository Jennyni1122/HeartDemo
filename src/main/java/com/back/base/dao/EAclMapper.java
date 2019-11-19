package com.back.base.dao;

import java.util.List;

import com.back.base.model.EAcl;

public interface EAclMapper extends BaseMapper<EAcl>{

	List<String> selectResourceIdByPrincipalIds(List<String> principalIds);

	/**
	 * 根据PrincipalId和ResourceType删除授权
	 * @param ecal
	 * @return
	 */
	int deleteByPrincipalIdAndResourceType(EAcl ecal);

}