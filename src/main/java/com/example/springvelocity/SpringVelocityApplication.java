package com.example.springvelocity;

import com.example.springvelocity.Email.Repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringVelocityApplication {
    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private VelocityEmailService velocityEmailService;

    public static void main(String[] args) {
        SpringApplication.run(SpringVelocityApplication.class, args);
    }
//      @EventListener(ApplicationReadyEvent.class)
//      public void trigger(){
//        String from="kyo.minia2@gmail.com";
//        String subject="Velocity Test";
//        Recipient recipient=new Recipient("moataz","tahri","kyo.minia1@gmail.com");
//        List<String> attachments= Arrays.asList(/*"C:\\Users\\kyo_m\\Desktop\\spiderman-miles-lost-in-space-4k-0f.jpg"*/);
//        String template="src/main/webapp/WEB-INF/pages/email.vm";
//        JSONObject params=null;
//        try {
//            params=new JSONObject("{'body':'hello there!','title':'how are you'," +
//                    "'backgroundImage':'https://img.freepik.com/free-vector/creative-coming-soon-teaser-background_23-2148894969.jpg?t=st=1658838118~exp=1658838718~hmac=635a88843b42bece7478df3f189c8ab0618668cde385b666455737ddbf0031e4&w=1060g'," +
//                    "'name':'moataz'}");
//        } catch (JSONException e) {
//            System.out.println("vous avez une erreur à votre Json objet");
//            e.printStackTrace();
//        }
//        List<Email> emails=emailService.getAllEmails();
//        for(Email email:emails){
//            AutomaticMessageTemplate message=new AutomaticMessageTemplate(from, recipient,email);
//            try {
//                velocityEmailService.sendEmailAutomatic(message);
//                //velocityEmailService.sendEmailManual(message2);
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }
//        }
//        //ManualMessageTemplate message2=new ManualMessageTemplate(from,recipients,subject,attachments,template,params,EmailType.NEWSLETTER);
//
//
//      }

}
