package com.example.springvelocity.Email.Repository;

import com.example.springvelocity.Email.Entity.Email;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends PagingAndSortingRepository<Email,Integer>,JpaRepository<Email,Integer> {
    Page<Email> findAllBySentIsFalse(Pageable pageable);
}
