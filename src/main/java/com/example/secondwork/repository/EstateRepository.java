package com.example.secondwork.repository;

import com.example.secondwork.model.Estate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstateRepository extends JpaRepository<Estate, Integer> {
}
