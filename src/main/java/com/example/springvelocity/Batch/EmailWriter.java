package com.example.springvelocity.Batch;

import com.example.springvelocity.Email.Entity.Email;
import com.example.springvelocity.Email.Repository.EmailRepository;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmailWriter implements ItemWriter<Email> {

    @Autowired
    private EmailRepository emailRepository;

    @Override
    public void write(List<? extends Email> emails) {
        emailRepository.saveAllAndFlush(emails);
    }
}
