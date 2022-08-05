package com.example.springvelocity;

import com.example.springvelocity.Enumeration.EmailType;
import com.example.springvelocity.Model.MessageTemplate;
import com.example.springvelocity.Entity.User;
import com.example.springvelocity.Model.MessageTemplateConfiguration;
import com.example.springvelocity.Service.Email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.util.JavaScriptUtils;

import javax.mail.MessagingException;
import java.util.*;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringVelocityApplication {
    @Autowired
    @Qualifier("velocity")
    private EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(SpringVelocityApplication.class, args);
    }
    @EventListener(ApplicationReadyEvent.class)
    public void trigger(){
        String from="kyo.minia2@gmail.com";
        String subject="Velocity Test";
        List<String> recipients=Arrays.asList("kyo.minia1@gmail.com"/*,"kyo.minia2@gmail.com","kyo.minia3@gmail.com"*/);
        List<String> attachments=Arrays.asList(
                /*"C:\\Users\\kyo_m\\Desktop\\spiderman-miles-lost-in-space-4k-0f.jpg"*/);
        String template="src/main/webapp/WEB-INF/pages/Home.vm";
        EmailType emailType=EmailType.TOKEN;
        String header="<div style='text-align:center;padding:15px;border-bottom:1px solid #edeff2'>" +
                "        <a href='https://khamsat.com' style='text-decoration:none;color:black' target='_blank' data-saferedirecturl='https://www.google.com/url?q=https://khamsat.com&amp;source=gmail&amp;ust=1659795538883000&amp;usg=AOvVaw1bZw69GP_P4WsZt_ILNBb4'>" +
                "          <img style='max-height:44px;border:0;outline:none;text-decoration:none' src='https://ci5.googleusercontent.com/proxy/ubUVSz8E8f6rkWqaEjZbvRqGRAgjflzq4ljMwJ9BIghLH-9upIbzTYm6TGsheLkEftjXJXgCj0HcolFkPchkAgB0dA=s0-d-e1-ft#https://khamsat.com/images/khamsat-dark-logo.png' alt='خمسات' class='CToWUd' data-bit='iit'>" +
                "        </a>" +
                "      </div>";
        String body="<div dir='rtl' style='padding:25px 25px 9px;margin:auto;max-width:570px;border-bottom:1px solid #edeff2'>" +
                "    <p style='margin-top:0'>مرحبا Kyo،</p>" +
                "    <p style='margin-top:0'>تم إيقاف الخدمات التي تقدمها بشكل مؤقت نظراً لعدم دخولك موقع خمسات لأكثر من شهرين.</p>" +
                "    <p style='margin-top:0'>يمكنك إعادة تفعيل خدماتك مجدداً بعد تسجيل الدخول للموقع، وذلك عبر الرابط التالي:</p>" +
                "    <div style='text-align:center;margin:1em'>" +
                "      <a href='https://khamsat.com/user/kyo-minia' style='text-decoration:none;color:#fff;display:inline-block;text-decoration:none;font-size:16px;background-color:#444;padding:10px 18px' rel='noopener noreferrer' target='_blank' data-saferedirecturl='https://www.google.com/url?q=https://khamsat.com/user/kyo-minia&amp;source=gmail&amp;ust=1659795538884000&amp;usg=AOvVaw3vBXqdi4sQdQwIhjSc_VBl'>تفعيل خدماتك</a>" +
                "    </div>" +
                "    <p style='margin-top:0'>تحتاج لمساعدة؟ لا تتردد في التواصل معنا بالرد على هذه الرسالة.</p>" +
                "</div>";
        String footer="<div dir='rtl' style='max-width:570px;margin:auto;padding:25px'>" +
                "          <p style='margin-top:0'>أطيب التحيات،<br>فريق خمسات<br>" +
                "            <a href='https://khamsat.com' style='text-decoration:none' target='_blank' data-saferedirecturl='https://www.google.com/url?q=https://khamsat.com&amp;source=gmail&amp;ust=1659795538884000&amp;usg=AOvVaw1vHsvyWSWM4K_EtTqxnVgo'>https://khamsat.com</a>" +
                "          </p><div class='yj6qo'></div><div class='adL'>" +
                "        </div></div>";
        MessageTemplateConfiguration configuration=new MessageTemplateConfiguration(header,body,footer);
        JSONObject params=null;
        try {
            params=new JSONObject("{'body':'hello there!','title':'how are you'," +
                    "'backgroundImage':'https://img.freepik.com/free-vector/creative-coming-soon-teaser-background_23-2148894969.jpg?t=st=1658838118~exp=1658838718~hmac=635a88843b42bece7478df3f189c8ab0618668cde385b666455737ddbf0031e4&w=1060g'," +
                    "'name':'moataz'}");
        } catch (JSONException e) {
            System.out.println("vous avez une erreur à votre Json objet");
            e.printStackTrace();
        }
        MessageTemplate message=new MessageTemplate(from, recipients, subject, attachments,configuration,emailType);
        MessageTemplate message2=new MessageTemplate(from,recipients,subject,attachments,template,params,EmailType.NOTIFICATION);
        try {
            emailService.sendEmail(message);
            emailService.sendEmail(message2);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
