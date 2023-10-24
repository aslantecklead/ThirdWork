package com.example.secondwork.repository;

import com.example.secondwork.model.ShowingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SceduleRepository  extends JpaRepository<ShowingSchedule, Integer> {
    List<ShowingSchedule> findByName(String name);
}
