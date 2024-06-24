package com.containerstore.prestonintegrations.ordercreation.OrderApplication.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue
    private int storeId;
    private String storeCode;
    private String storeNumber;
    private Long storePhone;
    private String storeName;
    private String shipMarket;
    private boolean isPickup;
    private boolean isPadTransit;
}
