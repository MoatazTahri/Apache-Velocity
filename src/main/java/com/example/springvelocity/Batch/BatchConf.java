package com.example.springvelocity.Batch;

import com.example.springvelocity.Email.Entity.Email;
import com.example.springvelocity.Email.Repository.EmailRepository;
import com.example.springvelocity.Email.Service.EmailService;
import com.example.springvelocity.Notification.Enumeration.EmailType;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableBatchProcessing
@Component
@Transactional
public class BatchConf {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private EmailService emailService;

    @Bean
    public Job emailSenderJob(){
        /*Email email=new Email("email.vm","{'name':'Moataz'}",Set.of("C:\\Users\\kyo_m\\Desktop\\20220509_233453.jpg"),"Velocity Test", EmailType.NOTIFICATION,false);
        System.out.println(emailService.addEmail(email));*/
        System.out.println(emailRepository.findAllBySentIsFalse(Pageable.ofSize(1)).getTotalElements());
        return jobBuilderFactory.get("emailSenderJob")
                .incrementer(new RunIdIncrementer())
                .flow(emailSenderStep())
                .end()
                .build();
    }

    @Bean
    public Step emailSenderStep() {
        return stepBuilderFactory.get("emailSenderStep")
                .<Email,Email>chunk(1)
                .reader(emailReader())
                .processor(emailProcessor())
                .writer(emailWriter())
                .build();
    }

    @Bean
    public ItemWriter<Email> emailWriter() {
        return new EmailWriter();
    }

    @Bean
    public ItemProcessor<Email,Email> emailProcessor() {
        return new EmailProcessor();
    }

    @Bean
    public ItemReader<Email> emailReader() {
        RepositoryItemReader<Email> reader=new RepositoryItemReader<>();
        reader.setRepository(emailRepository);
        reader.setMethodName("findAllBySentIsFalse");
        reader.setPageSize(1);
        reader.setSort(Map.of("id", Sort.Direction.ASC));
        return reader;
    }
}
