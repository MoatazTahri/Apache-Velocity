package com.leaf.networkMS.Service;

import com.leaf.networkMS.Entity.NetworkEntity;

public interface NetworkService {

    /**
     * add new network information if not exist to database
     * @param network {@link NetworkEntity}
     * @return the network added if not exist or return null otherwise
     */
    NetworkEntity addIfNotExist(NetworkEntity network);

    /**
     * find network in database by its public id and isp of organization
     * @param publicIp public ip
     * @param isp isp of network organization
     * @return the found network
     */
    NetworkEntity getNetworkByPublicIpAndOrgIsp(String publicIp, String isp);
}
