package com.back.base.service;

import com.back.base.model.EAcl;
import com.back.base.model.EMenu;
import com.back.base.model.EResource;

import java.util.List;

public interface AclService {
    public int save(EAcl EAcl);

    public List<EMenu> authorizationMenu(EAcl EAcl);

    public List<EResource> authorizationResource(EAcl EAcl);


    public List<EResource> authorizationTaskRole(EAcl EAcl);

    public boolean havePermit(String principalId,String resourceId);
}
