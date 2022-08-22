package com.example.springvelocity.Batch;

import com.example.springvelocity.Email.Entity.Email;
import com.example.springvelocity.Email.Repository.EmailRepository;
import com.example.springvelocity.Recipient.Entity.Recipient;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import javax.persistence.LockModeType;
import java.util.List;

public class EmailWriter implements ItemWriter<Email> {
    @Autowired
    private EmailRepository emailRepository;

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Override
    public void write(List<? extends Email> emails) {
        emailRepository.saveAllAndFlush(emails);
    }
}
