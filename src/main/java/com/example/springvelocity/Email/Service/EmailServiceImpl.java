package com.example.springvelocity.Email.Service;

import com.example.springvelocity.Email.Entity.Email;
import com.example.springvelocity.Email.Exception.EmailNotFoundException;
import com.example.springvelocity.Email.Repository.EmailRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private EmailRepository emailRepository;
    @Override
    public Email addEmail(Email email) {
        return emailRepository.save(email);
    }

    @SneakyThrows
    @Override
    public Email getEmailById(int id) {
        return emailRepository.findById(id).orElseThrow(()->new EmailNotFoundException("No email found"));
    }
}
