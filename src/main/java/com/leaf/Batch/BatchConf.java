package com.leaf.Batch;

import com.leaf.EmailMS.Entity.EmailEntity;
import com.leaf.EmailMS.Repository.EmailRepository;
import com.leaf.PropertiesConf.JobProperties;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

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
    private JobProperties jobProperties;

    @Bean
    public Job emailSenderJob() {
        return jobBuilderFactory.get(jobProperties.getJobName())
                .incrementer(new RunIdIncrementer())
                .flow(emailSenderStep())
                .end()
                .build();
    }

    @Bean
    public Step emailSenderStep() {
        return stepBuilderFactory.get(jobProperties.getStepName())
                .<EmailEntity, EmailEntity>chunk(1)
                .reader(emailReader())
                .processor(emailProcessor())
                .writer(emailWriter())
                .build();
    }

    @Bean
    public ItemWriter<EmailEntity> emailWriter() {
        return new EmailWriter();
    }

    @Bean
    public ItemProcessor<EmailEntity, EmailEntity> emailProcessor() {
        return new EmailProcessor();
    }

    @Bean
    public ItemReader<EmailEntity> emailReader() {
        RepositoryItemReader<EmailEntity> reader = new RepositoryItemReader<>();
        reader.setRepository(emailRepository);
        reader.setMethodName(jobProperties.getReader().getMethodName());
        reader.setPageSize(jobProperties.getReader().getPageSize());
        reader.setSort(Map.of("id", Sort.Direction.ASC));
        return reader;
    }
}
