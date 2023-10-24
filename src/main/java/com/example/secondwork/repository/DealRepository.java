package com.example.secondwork.repository;

import com.example.secondwork.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Integer> {
    List<Deal> findByName(String name);
}
