package com.containerstore.prestonintegrations.ordercreation.OrderApplication.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "vendor_holiday")
public class VendorHoliday {

    @Id
    @GeneratedValue
    private int id;
    private String holiday;
    private LocalDate holiday_date;
}
