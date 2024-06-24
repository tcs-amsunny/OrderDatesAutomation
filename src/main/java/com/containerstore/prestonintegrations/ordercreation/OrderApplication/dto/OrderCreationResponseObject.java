package com.containerstore.prestonintegrations.ordercreation.OrderApplication.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
public class OrderCreationResponseObject {

    private LocalDate orderEngDate;
    private LocalDate shipDate;
    private LocalDate availDate;
    private String vendorCode;
    private String errorMessage;
}
