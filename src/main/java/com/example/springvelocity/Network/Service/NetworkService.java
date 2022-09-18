package com.example.springvelocity.Network.Service;

import com.example.springvelocity.Network.Entity.Network;

public interface NetworkService {

    Network addIfNotExist(Network network);

    Network getNetworkByPublicIpAndOrgIsp(String publicIp,String isp);
}
