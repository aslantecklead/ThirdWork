package com.example.secondwork.repository;

import com.example.secondwork.model.Estate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstateRepository extends JpaRepository<Estate, Integer> {
    List<Estate> findByName(String name);
}
