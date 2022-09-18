package com.example.springvelocity.Network.Service;

import com.example.springvelocity.Network.Entity.Organization;
import com.example.springvelocity.Network.Repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository repository;

    @Override
    public Organization addOrgIfNotExist(Organization organization) {
        if (getOrgByIsp(organization.getIsp()) == null)
            return repository.save(organization);
        return null;
    }

    @Override
    public Organization getOrgByIsp(String isp) {
        return repository.findByIsp(isp);
    }
}
