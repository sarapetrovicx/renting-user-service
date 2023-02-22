package com.raf.restdemo.repository;

import com.raf.restdemo.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findUserByEmailAndPassword(String email, String password);

}
