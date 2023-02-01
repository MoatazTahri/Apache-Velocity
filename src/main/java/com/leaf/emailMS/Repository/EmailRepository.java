package com.leaf.emailMS.Repository;

import com.leaf.emailMS.Entity.EmailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends PagingAndSortingRepository<EmailEntity, Integer>, JpaRepository<EmailEntity, Integer> {

    /**
     * return all emails not sent yet
     *
     * @param pageable custom page for result
     * @return page of emails
     */
    Page<EmailEntity> findAllBySentIsFalse(Pageable pageable);
}
