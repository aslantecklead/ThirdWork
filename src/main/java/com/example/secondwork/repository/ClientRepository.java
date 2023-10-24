package com.example.secondwork.repository;

import com.example.secondwork.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByName(String name);
}
 