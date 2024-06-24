package com.containerstore.prestonintegrations.ordercreation.OrderApplication.repository;

import com.containerstore.prestonintegrations.ordercreation.OrderApplication.Entity.VendorHoliday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface VendorHolidayRepository extends JpaRepository<VendorHoliday, Integer> {

    @Query("SELECT s.holiday_date FROM VendorHoliday s")
    List<LocalDate> findAllManuHolidayDates();
}
