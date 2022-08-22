package com.example.springvelocity.Email.Service;

import com.example.springvelocity.Email.Entity.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmailService {
    Email addEmail(Email email);
    Email getEmailById(int id);
}
