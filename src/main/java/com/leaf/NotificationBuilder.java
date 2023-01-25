package com.leaf;

import com.leaf.DeviceMS.Entity.DeviceEntity;
import com.leaf.DeviceMS.Service.DeviceService;
import com.leaf.DeviceMS.Tools.DeviceUtils;
import com.leaf.EmailMS.Model.MessageTemplate;
import com.leaf.LocalizationMS.Entity.CityEntity;
import com.leaf.LocalizationMS.Service.CityService;
import com.leaf.NetworkMS.Entity.NetworkEntity;
import com.leaf.NetworkMS.Entity.OrganizationEntity;
import com.leaf.NetworkMS.Service.NetworkService;
import com.leaf.NetworkMS.Service.OrganizationService;
import com.leaf.NetworkMS.Tools.NetworkUtils;
import com.leaf.NotificationMS.Entity.NotificationEntity;
import com.leaf.Tools.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.InetAddress;

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
        InetAddress senderIp = InetAddress.getLocalHost();
        String macAddress = DeviceUtils.getAddressMac(senderIp);
        DeviceEntity device = DeviceEntity.builder()
                .localIp(senderIp.getHostAddress())
                .macAddress(macAddress)
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
