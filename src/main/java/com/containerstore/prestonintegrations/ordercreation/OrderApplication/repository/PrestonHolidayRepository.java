package com.containerstore.prestonintegrations.ordercreation.OrderApplication.repository;

import com.containerstore.prestonintegrations.ordercreation.OrderApplication.Entity.PrestonHoliday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PrestonHolidayRepository extends JpaRepository<PrestonHoliday, Integer> {

    @Query("SELECT p.holiday_date FROM PrestonHoliday p")
    List<LocalDate> findAllHolidayDates();
}