package com.example.springvelocity.Network.Repository;

import com.example.springvelocity.Network.Entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Integer> {
    Organization findByIsp(String isp);
}
