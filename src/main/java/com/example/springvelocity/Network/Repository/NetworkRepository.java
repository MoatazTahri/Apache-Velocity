package com.example.springvelocity.Network.Repository;

import com.example.springvelocity.Network.Entity.Network;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkRepository extends JpaRepository<Network,Integer> {
    Network findByPublicIpAndOrganizationIsp(String publicIp,String isp);
}
