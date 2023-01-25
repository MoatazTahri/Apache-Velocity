package com.leaf.NetworkMS.Repository;

import com.leaf.NetworkMS.Entity.NetworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkRepository extends JpaRepository<NetworkEntity, Integer> {

    NetworkEntity findByPublicIpAndOrganizationIsp(String publicIp, String isp);
}
