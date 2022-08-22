package com.example.springvelocity.Recipient.Repository;

import com.example.springvelocity.Recipient.Entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient,Integer> {
    Optional<Recipient> findByEmail(String email);
}
