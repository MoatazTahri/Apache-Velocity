package com.example.springvelocity.Network.Service;

import com.example.springvelocity.Network.Entity.Network;
import com.example.springvelocity.Network.Repository.NetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NetworkServiceImpl implements NetworkService{
    @Autowired
    private NetworkRepository repository;
    @Override
    public Network getNetworkByPublicIpAndOrgIsp(String publicIp,String isp) {
        return repository.findByPublicIpAndOrganizationIsp(publicIp,isp);
    }
    @Override
    public Network addIfNotExist(Network network) {
        if (getNetworkByPublicIpAndOrgIsp(network.getPublicIp(),network.getOrganization().getIsp())!=null)
            return null;
        return repository.save(network);
    }
}
