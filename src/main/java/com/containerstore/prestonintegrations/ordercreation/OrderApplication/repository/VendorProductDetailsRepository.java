package com.containerstore.prestonintegrations.ordercreation.OrderApplication.repository;

import com.containerstore.prestonintegrations.ordercreation.OrderApplication.Entity.VendorProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorProductDetailsRepository extends JpaRepository<VendorProductDetails, Integer>{

    List<VendorProductDetails> findByManufacturingCode(String manufacturingCode);
}
