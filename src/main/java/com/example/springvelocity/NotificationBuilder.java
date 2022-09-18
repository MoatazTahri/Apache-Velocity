package com.example.springvelocity;

import com.example.springvelocity.Device.Entity.Device;
import com.example.springvelocity.Device.Service.DeviceService;
import com.example.springvelocity.Email.Model.MessageTemplate;
import com.example.springvelocity.Localization.Entity.City;
import com.example.springvelocity.Localization.Service.CityService;
import com.example.springvelocity.Network.Entity.Network;
import com.example.springvelocity.Network.Entity.Organization;
import com.example.springvelocity.Network.Service.NetworkService;
import com.example.springvelocity.Network.Service.OrganizationService;
import com.example.springvelocity.Notification.Entity.Notification;
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


    public Notification build(MimeMessage mimeMessage, MessageTemplate message) throws MessagingException, JSONException, IOException {
        return notificationBuilder(mimeMessage, message);
    }

    private Notification notificationBuilder(MimeMessage mimeMessage, MessageTemplate message) throws IOException, JSONException, MessagingException {
        String publicIp = Methods.getPublicIp();
        return Notification.builder()
                .emailFrom(message.getFrom())
                .device(deviceBuilder())
                .network(networkBuilder(publicIp))
                .sentDate(mimeMessage.getSentDate())
                .city(cityBuilder(publicIp))
                .email(message.getEmail())
                .build();
    }


    private Device deviceBuilder() throws IOException {
        InetAddress senderIp = InetAddress.getLocalHost();
        String macAddress = Methods.getAddressMac(senderIp);// trouver l'adresse MAC
        Device device = Device.builder()
                .localIp(senderIp.getHostAddress())
                .macAddress(macAddress)
                .build();
        deviceService.addIfNotExist(device);
        return deviceService.getDeviceByIpAndMac(device.getLocalIp(), device.getMacAddress());
    }

    private Network networkBuilder(String publicIp) throws IOException, JSONException {
        Organization organization = organizationBuilder(publicIp);
        Network network = Network.builder()
                .publicIp(publicIp)
                .organization(organization)
                .build();
        networkService.addIfNotExist(network);
        return networkService.getNetworkByPublicIpAndOrgIsp(publicIp, organization.getIsp());

    }

    private Organization organizationBuilder(String publicIp) throws JSONException, IOException {
        JSONObject organisationObject = Methods.getJSON("http://ip-api.com/json/" + publicIp);
        String org = organisationObject.getString("as");
        String isp = organisationObject.getString("isp");
        Organization organization = Organization.builder()
                .name(org)
                .isp(isp)
                .build();
        organizationService.addOrgIfNotExist(organization);
        return organizationService.getOrgByIsp(isp);
    }

    private City cityBuilder(String publicIp) throws JSONException, IOException {
        JSONObject cityObject = Methods.getJSON("https://ipinfo.io/" + publicIp + "/json");
        String cityName = cityObject.getString("city");
        String countryCode = cityObject.getString("country");
        return cityService.getCityByCountryAndName(countryCode, cityName);
    }
}
