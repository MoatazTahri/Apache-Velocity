package com.example.springvelocity.Service.Email;

import com.example.springvelocity.Entity.*;
import com.example.springvelocity.Enumeration.EmailConfigurationType;
import com.example.springvelocity.Methods;
import com.example.springvelocity.Model.MessageTemplate;
import com.example.springvelocity.Service.Notification.NotificationService;
import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.InternalClient;
import com.microsoft.azure.sdk.iot.device.twin.DeviceTwin;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service("velocity")
@AllArgsConstructor
@Slf4j
public class VelocityEmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private VelocityEngine velocityEngine;
    private NotificationService notificationService;
    @Override
    public void sendEmail(final MessageTemplate message) {
        MimeMessagePreparator preparator= mimeMessage -> {
            MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage,true);
            // Test de la nullabilité des éléments essentiels
            if(CollectionUtils.isEmpty(message.getTo())){
                throw new IllegalStateException("No recipient found for your email");
            }
            String template=message.getTemplate();
            if(velocityEngine.getTemplate(template)==null){
                throw new MessagingException("the template doesn't exist");
            }
            // Test d'élément facultatif avec l'initialisation si pas null
            if(CollectionUtils.isNotEmpty(message.getAttachments())){
                for (String attachment:message.getAttachments()){
                    FileSystemResource resource=new FileSystemResource(attachment);
                    messageHelper.addAttachment(resource.getFilename(),resource);
                }
            }
            // Initialisation d'Email et de Template
            for(String address:message.getTo()){
                mimeMessage.addRecipient(Message.RecipientType.BCC,new InternetAddress(address));
            }
            messageHelper.setFrom(new InternetAddress(message.getFrom()));
            messageHelper.setSubject(message.getSubject());
            messageHelper.setSentDate(new Date());
            VelocityContext context=new VelocityContext();
            //context.put("backgroundImage","https://img.freepik.com/free-vector/creative-coming-soon-teaser-background_23-2148894969.jpg?t=st=1658838118~exp=1658838718~hmac=635a88843b42bece7478df3f189c8ab0618668cde385b666455737ddbf0031e4&w=1060g");
            if(message.getParams()!=null){
                Iterator iterator=message.getParams().keys();
                while (iterator.hasNext()){
                    Object i=iterator.next();
                    context.put(i.toString(),message.getParams().get(i.toString()));
                }
            }
            EmailConfigurationType configurationType=EmailConfigurationType.MANUAL;
            if(message.getConfiguration()!=null){
                configurationType=EmailConfigurationType.AUTOMATIC;
                context.put("header","");
                if(StringUtils.isNotEmpty(message.getConfiguration().getHeader())){
                    context.put("header",message.getConfiguration().getHeader());
                }
                context.put("body","");
                if(StringUtils.isNotEmpty(message.getConfiguration().getBody())){
                    context.put("body",message.getConfiguration().getBody());
                }
                context.put("footer","");
                if(StringUtils.isNotEmpty(message.getConfiguration().getFooter())){
                    context.put("footer",message.getConfiguration().getFooter());
                }
            }
            SimpleDateFormat sdf=new SimpleDateFormat("EEEE dd-MM-yyyy HH:mm:ss", Locale.FRANCE);
            context.put("now",sdf.format(mimeMessage.getSentDate()));
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
            JSONObject object= Methods.getJSON("https://ipinfo.io/"+publicIp+"/json");
            String city=object.getString("city");
            String region=object.getString("region");
            String country=object.getString("country");
            String organization=object.getString("org");
            String timeZone=object.getString("timezone");
            String coordinates=object.getString("loc");
            Device device=new Device(senderIp.getHostAddress(),macAddress);
            Localization localization=new Localization(country,city,region,coordinates,timeZone);
            Network network=new Network(localization,publicIp,organization);
            Notification notification=new Notification(mimeMessage.getFrom()[0].toString(),message.getEmailType(),device,network,mimeMessage.getSentDate(),configurationType);
            Set<Recipient> recipients=message.getTo().stream().map(recipient->{
                Recipient notificationRecipient=new Recipient();
                notificationRecipient.setEmailTo(recipient);
                return notificationRecipient;
            }).collect(Collectors.toSet());
            notification.setRecipients(recipients);
            notificationService.addNotification(notification);
        };
        mailSender.send(preparator);
        Map<String,String> environmentVariables=System.getenv();
        List<String> keys=environmentVariables.keySet().stream().collect(Collectors.toList());
        List<String> variables=environmentVariables.values().stream().collect(Collectors.toList());
        for(int i=0;i<environmentVariables.size();i++){
            System.out.println(keys.get(i)+":"+variables.get(i));
        }
        System.out.println("email sent");
    }
}
