package com.example.springvelocity.Recipient.Service;

import com.example.springvelocity.Notification.Exception.RecipientNotFoundException;
import com.example.springvelocity.Recipient.Entity.Recipient;
import com.example.springvelocity.Recipient.Repository.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipientServiceImpl implements RecipientService {
    @Autowired
    private RecipientRepository repository;

    @Override
    public Recipient getRecipientByEmail(String email) throws RecipientNotFoundException {
        return repository.findByEmail(email).orElseThrow(()->new RecipientNotFoundException("No recipient exists has this email"));
    }

    @Override
    public List<Recipient> getAllRecipients() {
        return repository.findAll();
    }

    @Override
    public Recipient addRecipient(Recipient recipient) {
        return repository.save(recipient);
    }
}
