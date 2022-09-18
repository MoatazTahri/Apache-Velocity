package com.example.springvelocity.Email.Repository;

import com.example.springvelocity.Email.Entity.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends PagingAndSortingRepository<Email,Integer>, JpaRepository<Email,Integer> {

    /**
     * return all emails not sent yet
     * @param pageable custom page for result
     * @return page of emails
     */
    Page<Email> findAllBySentIsFalse(Pageable pageable);
}
