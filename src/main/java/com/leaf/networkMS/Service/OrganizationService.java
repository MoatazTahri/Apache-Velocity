package com.leaf.networkMS.Service;

import com.leaf.networkMS.Entity.OrganizationEntity;

public interface OrganizationService {
    /**
     * add new network organization information if not exist to database
     * @param organization {@link OrganizationEntity}
     * @return  the network organization added if not exist or return null otherwise
     */
    OrganizationEntity addOrgIfNotExist(OrganizationEntity organization);

    /**
     * find network organization in database by its isp
     * @param isp organization unique isp
     * @return the found network organization
     */
    OrganizationEntity getOrgByIsp(String isp);
}
