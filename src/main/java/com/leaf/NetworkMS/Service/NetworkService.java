package com.leaf.NetworkMS.Service;

import com.leaf.NetworkMS.Entity.NetworkEntity;

public interface NetworkService {

    NetworkEntity addIfNotExist(NetworkEntity network);

    NetworkEntity getNetworkByPublicIpAndOrgIsp(String publicIp, String isp);
}
