package com.example.secondwork.repository;

import com.example.secondwork.model.ShowingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SceduleRepository  extends JpaRepository<ShowingSchedule, Integer> {
}
