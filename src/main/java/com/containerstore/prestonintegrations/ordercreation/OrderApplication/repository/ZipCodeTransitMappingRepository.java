package com.containerstore.prestonintegrations.ordercreation.OrderApplication.repository;

import com.containerstore.prestonintegrations.ordercreation.OrderApplication.Entity.ZipCodeTransitMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZipCodeTransitMappingRepository extends JpaRepository<ZipCodeTransitMapping, Integer> {

    ZipCodeTransitMapping findByZipCode(String zipCode);
}
