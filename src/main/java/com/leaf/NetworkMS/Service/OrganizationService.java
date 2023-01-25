package com.leaf.NetworkMS.Service;

import com.leaf.NetworkMS.Entity.OrganizationEntity;

public interface OrganizationService {
    OrganizationEntity addOrgIfNotExist(OrganizationEntity organization);

    OrganizationEntity getOrgByIsp(String isp);
}
