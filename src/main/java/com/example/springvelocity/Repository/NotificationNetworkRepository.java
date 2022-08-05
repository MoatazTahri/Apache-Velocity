package com.example.springvelocity.Repository;

import com.example.springvelocity.Entity.Network;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationNetworkRepository extends JpaRepository<Network,Integer> {
}
