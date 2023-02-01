package com.leaf.service;

import com.leaf.deviceMS.Entity.DeviceEntity;
import com.leaf.deviceMS.Service.DeviceService;
import com.leaf.deviceMS.Tools.DeviceUtils;
import com.leaf.emailMS.Model.MessageTemplate;
import com.leaf.localizationMS.Entity.CityEntity;
import com.leaf.localizationMS.Service.CityService;
import com.leaf.networkMS.Entity.NetworkEntity;
import com.leaf.networkMS.Entity.OrganizationEntity;
import com.leaf.networkMS.Service.NetworkService;
import com.leaf.networkMS.Service.OrganizationService;
import com.leaf.networkMS.Tools.NetworkUtils;
import com.leaf.notificationMS.Entity.NotificationEntity;
import com.leaf.tools.APILinks;
import com.leaf.tools.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Component
public class NotificationBuilder {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private NetworkService networkService;

    @Autowired
    private CityService cityService;


    public NotificationEntity build(MimeMessage mimeMessage, MessageTemplate message) throws MessagingException, JSONException, IOException {
        return notificationBuilder(mimeMessage, message);
    }

    private NotificationEntity notificationBuilder(MimeMessage mimeMessage, MessageTemplate message) throws IOException, JSONException, MessagingException {
        String publicIp = NetworkUtils.getPublicIp();
        return NotificationEntity.builder()
                .emailFrom(message.getFrom())
                .device(deviceBuilder())
                .network(networkBuilder(publicIp))
                .sentDate(mimeMessage.getSentDate())
                .city(cityBuilder(publicIp))
                .email(message.getEmail())
                .build();
    }


    private DeviceEntity deviceBuilder() throws IOException {
        DeviceUtils deviceUtils = DeviceUtils.getInstance();
        DeviceEntity device = DeviceEntity.builder()
                .localIp(deviceUtils.getHostAddress())
                .macAddress(deviceUtils.getAddressMac())
                .processor(deviceUtils.getProcessor())
                .model(deviceUtils.getModel())
                .manufacturer(deviceUtils.getManufacturer())
                .operatingSystem(deviceUtils.getOperatingSystem())
                .build();
        deviceService.addIfNotExist(device);
        return deviceService.getDeviceByIpAndMac(device.getLocalIp(), device.getMacAddress());
    }

    private NetworkEntity networkBuilder(String publicIp) throws IOException, JSONException {
        OrganizationEntity organization = organizationBuilder(publicIp);
        NetworkEntity network = NetworkEntity.builder()
                .publicIp(publicIp)
                .organization(organization)
                .build();
        networkService.addIfNotExist(network);
        return networkService.getNetworkByPublicIpAndOrgIsp(publicIp, organization.getIsp());

    }

    private OrganizationEntity organizationBuilder(String publicIp) throws JSONException, IOException {
        JSONObject organisationObject = JsonUtils.getJSON(String.format(APILinks.IPApi_Com, publicIp));
        String org = organisationObject.getString("as");
        String isp = organisationObject.getString("isp");
        OrganizationEntity organization = OrganizationEntity.builder()
                .name(org)
                .isp(isp)
                .build();
        organizationService.addOrgIfNotExist(organization);
        return organizationService.getOrgByIsp(isp);
    }

    private CityEntity cityBuilder(String publicIp) throws JSONException, IOException {
        JSONObject cityObject = JsonUtils.getJSON(String.format(APILinks.IPInfo_IO, publicIp));
        String cityName = cityObject.getString("city");
        String countryCode = cityObject.getString("country");
        return cityService.getCityByCountryAndName(countryCode, cityName);
    }
}
