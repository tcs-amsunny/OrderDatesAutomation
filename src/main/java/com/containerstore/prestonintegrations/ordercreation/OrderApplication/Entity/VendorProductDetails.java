package com.containerstore.prestonintegrations.ordercreation.OrderApplication.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vendor_product_details")
public class VendorProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "vendor_name")
    private String vendorName;
    @Column(name = "product_desc")
    private String productDesc;
    @Column(name = "manufacturing_code")
    private String manufacturingCode;
    @Column(name = "manufacturing_total_biz_days")
    private long manufacturingTotalBizDays;
    @Column(name = "replacement_manufacturing_code")
    private String replacementManufacturingCode;
    @Column(name = "replacement_manufacturing_total_biz_days")
    private long replacementManufacturingTotalBizDays;
}
