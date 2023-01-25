package com.leaf.Batch;

import com.leaf.EmailMS.Entity.EmailEntity;
import com.leaf.EmailMS.Repository.EmailRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmailWriter implements ItemWriter<EmailEntity> {

    @Autowired
    private EmailRepository emailRepository;

    @Override
    public void write(List<? extends EmailEntity> emails) {
        emailRepository.saveAllAndFlush(emails);
    }
}
