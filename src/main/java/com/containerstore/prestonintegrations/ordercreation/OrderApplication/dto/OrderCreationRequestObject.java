package com.containerstore.prestonintegrations.ordercreation.OrderApplication.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class OrderCreationRequestObject {
        private String storeCode;
        private String zipCode;
        private int pcLeadTime;
        private LocalDate shipDate;
        private LocalDate installationDate;
        private LocalDate replacementOrderDate;
        private List<SpecialProduct> specialProduct;
    }

