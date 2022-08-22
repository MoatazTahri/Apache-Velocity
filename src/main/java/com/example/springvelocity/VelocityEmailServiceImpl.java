package com.example.springvelocity;

import com.example.springvelocity.Device.Entity.Device;
import com.example.springvelocity.Device.Service.DeviceService;
import com.example.springvelocity.Localization.Entity.City;
import com.example.springvelocity.Localization.Service.CityService;
import com.example.springvelocity.Network.Entity.Organization;
import com.example.springvelocity.Network.Service.NetworkService;
import com.example.springvelocity.Network.Service.OrganizationService;
import com.example.springvelocity.Email.Model.AutomaticMessageTemplate;
import com.example.springvelocity.Network.Entity.Network;
import com.example.springvelocity.Notification.Entity.Notification;
import com.example.springvelocity.Email.Model.ManualMessageTemplate;
import com.example.springvelocity.Notification.Service.NotificationService;
import com.example.springvelocity.Recipient.Entity.Recipient;
import com.example.springvelocity.Recipient.Service.RecipientService;
import lombok.AllArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.*;
import java.util.*;

@Service
@AllArgsConstructor
public class VelocityEmailServiceImpl implements VelocityEmailService {

    private RecipientService recipientService;

    private JavaMailSender mailSender;

    private VelocityEngine velocityEngine;

    private NotificationService notificationService;

    private DeviceService deviceService;

    private OrganizationService organizationService;

    private NetworkService networkService;

    private CityService cityService;


    // disabled function for now
    @Override
    public void sendEmailAutomatic(final AutomaticMessageTemplate message) {
        /*MimeMessagePreparator preparator= mimeMessage -> {
            MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage,true);
            // Test de la nullabilité des éléments essentiels
            if(StringUtils.isEmpty(message.getRecipientEmailAddress())){
                throw new IllegalStateException("No recipient found for your email :"+message.getRecipientEmailAddress());
            }
            String template=message.getTemplate();
            if(velocityEngine.getTemplate(template)==null){
                throw new MessagingException("the template doesn't exist");
            }
            // Test d'élément facultatif avec l'initialisation si pas null
            if(StringUtils.isNotEmpty(message.getEmail().getAttachments())){
                List<String> attachements=Arrays.asList(message.getEmail().getAttachments().split(","));
                for (String attachment:attachements){
                    FileSystemResource resource=new FileSystemResource(attachment);
                    messageHelper.addAttachment(resource.getFilename(),resource);
                }
            }
            // Initialisation d'Email et de Template
            mimeMessage.addRecipient(Message.RecipientType.BCC,new InternetAddress(message.getRecipientEmailAddress()));
            messageHelper.setFrom(new InternetAddress(message.getFrom()));
            messageHelper.setSubject(message.getEmail().getSubject());
            messageHelper.setSentDate(new Date());
            VelocityContext context=new VelocityContext();
            //context.put("backgroundImage","https://img.freepik.com/free-vector/creative-coming-soon-teaser-background_23-2148894969.jpg?t=st=1658838118~exp=1658838718~hmac=635a88843b42bece7478df3f189c8ab0618668cde385b666455737ddbf0031e4&w=1060g");
            context.put("header","");
            if(StringUtils.isNotEmpty(message.getEmail().getHeader())){
                context.put("header",message.getEmail().getHeader());
            }
            context.put("body","");
            if(StringUtils.isNotEmpty(message.getEmail().getBody())){
                context.put("body",message.getEmail().getBody());
            }
            context.put("footer","");
            if(StringUtils.isNotEmpty(message.getEmail().getFooter())){
                context.put("footer",message.getEmail().getFooter());
            }
            context.put("mentions","");
            if(StringUtils.isNotEmpty(message.getEmail().getMentions())){
                context.put("mentions",message.getEmail().getMentions());
            }
            StringWriter stringWriter=new StringWriter();
            velocityEngine.mergeTemplate(template,"UTF-8",context,stringWriter);
            messageHelper.setText(stringWriter.toString(),true);
            // Chercher les données concernant l'Email
            InetAddress senderIp=InetAddress.getLocalHost();
            URL myIpURL = new URL("https://checkip.amazonaws.com/"); //trouver l'ip public
            BufferedReader br = new BufferedReader(new InputStreamReader(myIpURL.openStream()));
            String publicIp = br.readLine();
            String macAddress=Methods.getAddressMac(senderIp);// trouver l'adresse MAC
            // Les données géographiques de l'envoyeur d'Email
            JSONObject object= Methods.getJSON("http://ip-api.com/json/"+publicIp);
            JSONObject object1=Methods.getJSON("https://ipinfo.io/"+publicIp+"/json");
            String cityName=object1.getString("city");
            String countryCode=object.getString("countryCode");
            City city=cityService.getCityByCountryAndName(countryCode,cityName);
            String org=object.getString("as");
            String isp=object.getString("isp");
            deviceService.addIfNotExist(new Device(senderIp.getHostAddress(),macAddress));
            Device device=deviceService.getDeviceByIpAndMac(senderIp.getHostAddress(),macAddress);
            organizationService.addOrgIfNotExist(new Organization(org,isp));
            Organization organization=organizationService.getOrgByIsp(isp);
            networkService.addIfNotExist(new Network(publicIp,organization));
            Network network=networkService.getNetworkByPublicIpAndOrgIsp(publicIp,isp);
            Recipient recipient=recipientService.getRecipientByEmail(message.getRecipientEmailAddress());
            Notification notification=new Notification(mimeMessage.getFrom()[0].toString(),device,network,mimeMessage.getSentDate(),recipient,city,message.getEmail());
            notificationService.addNotification(notification);
        };
        mailSender.send(preparator);
        System.out.println("email sent");*/
    }

    @Override
    public void sendEmailManual(ManualMessageTemplate message){
        MimeMessagePreparator preparator= mimeMessage -> {
            MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage,true);
            // Test de la nullabilité des éléments essentiels
            if(StringUtils.isEmpty(message.getRecipientEmailAddress())){
                throw new IllegalStateException("No recipient found for your email :"+message.getRecipientEmailAddress());
            }
            String template="src/main/webapp/WEB-INF/pages/"+message.getEmail().getTemplateName();
            if(!velocityEngine.resourceExists(template)){
                throw new MessagingException("the template doesn't exist");
            }
            // Test d'élément facultatif, avec l'initialisation si non null
            if(CollectionUtils.isNotEmpty(message.getEmail().getAttachments())){
                for (String attachment:message.getEmail().getAttachments()){
                    FileSystemResource resource=new FileSystemResource(attachment);
                    messageHelper.addAttachment(resource.getFilename(),resource);
                }
            }
            // Initialisation d'Email et de Template
            mimeMessage.addRecipient(Message.RecipientType.CC,new InternetAddress(message.getRecipientEmailAddress()));
            messageHelper.setFrom(new InternetAddress(message.getFrom()));
            messageHelper.setSubject(message.getEmail().getSubject());
            messageHelper.setSentDate(new Date());
            VelocityContext context=new VelocityContext();
            //context.put("backgroundImage","https://img.freepik.com/free-vector/creative-coming-soon-teaser-background_23-2148894969.jpg?t=st=1658838118~exp=1658838718~hmac=635a88843b42bece7478df3f189c8ab0618668cde385b666455737ddbf0031e4&w=1060g");
            if(message.getEmail().getJsonParams()!=null){
                JSONObject params=new JSONObject(message.getEmail().getJsonParams());
                Iterator iterator=params.keys();
                while (iterator.hasNext()){
                    Object i=iterator.next();
                    context.put(i.toString(),params.get(i.toString()));
                }
            }
            StringWriter stringWriter=new StringWriter();
            velocityEngine.mergeTemplate(template,"UTF-8",context,stringWriter);
            messageHelper.setText(stringWriter.toString(),true);
            // Chercher les données concernant l'Email
            InetAddress senderIp=InetAddress.getLocalHost();
            URL myIpURL = new URL("https://checkip.amazonaws.com/"); //trouver l'ip public
            BufferedReader br = new BufferedReader(new InputStreamReader(myIpURL.openStream()));
            String publicIp = br.readLine();
            String macAddress=Methods.getAddressMac(senderIp);// trouver l'adresse MAC
            // Les données géographiques de l'envoyeur d'Email
            JSONObject object= Methods.getJSON("http://ip-api.com/json/"+publicIp);
            JSONObject object1=Methods.getJSON("https://ipinfo.io/"+publicIp+"/json");
            String cityName=object1.getString("city");
            String countryCode=object.getString("countryCode");
            City city=cityService.getCityByCountryAndName(countryCode,cityName);
            String org=object.getString("as");
            String isp=object.getString("isp");
            deviceService.addIfNotExist(new Device(senderIp.getHostAddress(),macAddress));
            Device device=deviceService.getDeviceByIpAndMac(senderIp.getHostAddress(),macAddress);
            organizationService.addOrgIfNotExist(new Organization(org,isp));
            Organization organization=organizationService.getOrgByIsp(isp);
            networkService.addIfNotExist(new Network(publicIp,organization));
            Network network=networkService.getNetworkByPublicIpAndOrgIsp(publicIp,isp);
            Recipient recipient=recipientService.getRecipientByEmail(message.getRecipientEmailAddress());
            Notification notification=new Notification(mimeMessage.getFrom()[0].toString(),device,network,mimeMessage.getSentDate(),recipient,city,message.getEmail());
            notificationService.addNotification(notification);
        };
        mailSender.send(preparator);
        System.out.println("email sent");
    }
}
