package com.example.secondwork.repository;

import com.example.secondwork.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
    List<Offer> findByName(String name);
}
