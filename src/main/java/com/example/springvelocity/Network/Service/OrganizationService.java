package com.example.springvelocity.Network.Service;

import com.example.springvelocity.Network.Entity.Organization;

public interface OrganizationService {
    Organization addOrgIfNotExist(Organization organization);
    Organization getOrgByIsp(String isp);
}
