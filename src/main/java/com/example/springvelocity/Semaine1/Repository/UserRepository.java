package com.example.springvelocity.Semaine1.Repository;

import com.example.springvelocity.Semaine1.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
