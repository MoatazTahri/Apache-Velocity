package com.leaf.NetworkMS.Service;

import com.leaf.NetworkMS.Entity.OrganizationEntity;
import com.leaf.NetworkMS.Repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository repository;

    @Override
    public OrganizationEntity addOrgIfNotExist(OrganizationEntity organization) {
        if (getOrgByIsp(organization.getIsp()) == null) {
            return repository.save(organization);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("organization")
    public OrganizationEntity getOrgByIsp(String isp) {
        return repository.findByIsp(isp);
    }
}
