package com.containerstore.prestonintegrations.ordercreation.OrderApplication.repository;

import com.containerstore.prestonintegrations.ordercreation.OrderApplication.Entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    @Query("SELECT s.isPadTransit FROM Store s WHERE s.storeCode = :storeCode")
    Boolean findIsPadTransitByStoreCode(@Param("storeCode") String storeCode);

    @Query("SELECT s.isPickup FROM Store s WHERE s.storeCode = :storeCode ")
    Boolean findIsPickupByStoreCode(@Param("storeCode") String storeCode);
}