package com.leaf.networkMS.Service;

import com.leaf.networkMS.Entity.NetworkEntity;
import com.leaf.networkMS.Repository.NetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NetworkServiceImpl implements NetworkService {

    @Autowired
    private NetworkRepository repository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable("network")
    public NetworkEntity getNetworkByPublicIpAndOrgIsp(String publicIp, String isp) {
        return repository.findByPublicIpAndOrganizationIsp(publicIp, isp);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public NetworkEntity addIfNotExist(NetworkEntity network) {
        if (getNetworkByPublicIpAndOrgIsp(network.getPublicIp(), network.getOrganization().getIsp()) != null)
            return null;
        return repository.save(network);
    }
}
