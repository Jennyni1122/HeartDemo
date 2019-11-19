package com.back.base.service;

import com.back.base.model.EResource;

import java.util.List;

public interface ResourceService {
    public EResource find(String id);

    public List<EResource> list(EResource resource);

    public EResource saveOrUpdate(EResource resource);

    public int delete(String id);
}
