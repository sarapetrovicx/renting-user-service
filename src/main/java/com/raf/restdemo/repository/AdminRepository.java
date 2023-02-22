package com.raf.restdemo.repository;

import com.raf.restdemo.domain.Admin;
import com.raf.restdemo.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findUserByEmailAndPassword(String email, String password);

}
