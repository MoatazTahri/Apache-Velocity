package com.leaf.networkMS.Repository;

import com.leaf.networkMS.Entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity,Integer> {

    OrganizationEntity findByIsp(String isp);
}
