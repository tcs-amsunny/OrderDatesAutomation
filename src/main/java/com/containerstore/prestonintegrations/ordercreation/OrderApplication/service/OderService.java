package com.containerstore.prestonintegrations.ordercreation.OrderApplication.service;


import com.containerstore.prestonintegrations.ordercreation.OrderApplication.Entity.VendorProductDetails;
import com.containerstore.prestonintegrations.ordercreation.OrderApplication.dto.OrderCreationRequestObject;
import com.containerstore.prestonintegrations.ordercreation.OrderApplication.dto.OrderCreationResponseObject;
import com.containerstore.prestonintegrations.ordercreation.OrderApplication.dto.SpecialProduct;
import com.containerstore.prestonintegrations.ordercreation.OrderApplication.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class OderService {

    @Autowired
    private VendorProductDetailsRepository vendorRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private PrestonHolidayRepository prestonHolidayRepository;

    @Autowired
    private VendorHolidayRepository vendorHolidayRepository;

    @Autowired
    private ZipCodeTransitMappingRepository zipCodeTransitMappingRepository;

    public OrderCreationResponseObject forwardDatesCalculator(OrderCreationRequestObject request) {
        OrderCreationResponseObject response = new OrderCreationResponseObject();
        String storeCode = request.getStoreCode().toUpperCase();
        boolean isPickup = storeRepository.findIsPickupByStoreCode(storeCode);

        if (request.getReplacementOrderDate() == null) {
            // Condition 1 and 2: No replacement order date
            LocalDate orderEngDate = calculateOrderEngDate(request.getPcLeadTime());
            LocalDate shipDate = calculateShipDate(orderEngDate, request.getSpecialProduct(), request.getReplacementOrderDate());
            response.setOrderEngDate(orderEngDate);
            response.setShipDate(shipDate);
            response.setAvailDate(isPickup ? shipDate : calculateAvailDate(shipDate, request));
            response.setVendorCode(calculateManufacturingVendorCode(request.getSpecialProduct()));
        } else {
            // Condition 3 and 4: Replacement order date
            LocalDate shipDate = calculateShipDate(request.getReplacementOrderDate(), request.getSpecialProduct(), request.getReplacementOrderDate());
            response.setShipDate(shipDate);
            response.setVendorCode(calculateReplacementVendorCode(request.getSpecialProduct()));
            response.setAvailDate(isPickup ? shipDate : calculateAvailDate(shipDate, request));
        }
        return response;
    }

    public OrderCreationResponseObject backwardCalculator(OrderCreationRequestObject request) {
        OrderCreationResponseObject response = new OrderCreationResponseObject();
        LocalDate installationDate = request.getInstallationDate();
        if (!isBusinessDay(installationDate, prestonHolidayRepository.findAllHolidayDates())) {
            response.setErrorMessage("The installation date is not a business day.");
            return response;
        }
        String storeCode = request.getStoreCode().toUpperCase();
        boolean isPickup = storeRepository.findIsPickupByStoreCode(storeCode);
        response.setAvailDate(installationDate);
        response.setVendorCode(calculateManufacturingVendorCode(request.getSpecialProduct()));
        List<LocalDate> holidays = prestonHolidayRepository.findAllHolidayDates();
        List<LocalDate> manuHolidays = vendorHolidayRepository.findAllManuHolidayDates();
        int transitDays = numberOfTransitAndPadTransitDays(request);
        long manufacturingDays = maxManufacturingDays(request.getSpecialProduct())
                .map(VendorProductDetails::getManufacturingTotalBizDays)
                .orElse(0L);
        if (!isPickup) {
            LocalDate shipDate = calculatePastBusinessDate(installationDate, holidays, transitDays);
            LocalDate orderEngDate = calculatePastBusinessDate(shipDate, manuHolidays, manufacturingDays);
            response.setShipDate(shipDate);
            response.setOrderEngDate(orderEngDate);
        } else {
            LocalDate orderEngDate = calculatePastBusinessDate(installationDate, manuHolidays, manufacturingDays);
            response.setShipDate(installationDate);
            response.setOrderEngDate(orderEngDate);
        }
        return response;
    }

    public OrderCreationResponseObject shipDateCalculatorOnly(OrderCreationRequestObject request) {
        OrderCreationResponseObject response = new OrderCreationResponseObject();
        LocalDate shipDate = request.getShipDate();
        if (!isBusinessDay(shipDate, prestonHolidayRepository.findAllHolidayDates())) {
            response.setErrorMessage("The ship date is not a business day.");
            return response;
        }
        String storeCode = request.getStoreCode().toUpperCase();
        Boolean isPickup = storeRepository.findIsPickupByStoreCode(storeCode);
        response.setShipDate(shipDate);
        response.setAvailDate(isPickup? shipDate : calculateAvailDate(shipDate,request));
        return response;
    }

    private LocalDate calculateOrderEngDate(int pcLeadTime) {
        LocalDate today = LocalDate.now();
        List<LocalDate> holidays = prestonHolidayRepository.findAllHolidayDates();
        return calculateFutureBusinessDate(today, holidays, pcLeadTime);
    }

    private LocalDate calculateShipDate(LocalDate startDate, List<SpecialProduct> specialProduct, LocalDate replacementOrderDate) {
        List<LocalDate> manuHolidays = vendorHolidayRepository.findAllManuHolidayDates();
        long manufacturingDays = maxManufacturingDays(specialProduct)
                .map(vpd -> replacementOrderDate == null ? vpd.getManufacturingTotalBizDays() : vpd.getReplacementManufacturingTotalBizDays())
                .orElse(0L);
        return calculateFutureBusinessDate(startDate, manuHolidays, manufacturingDays);
    }

    private LocalDate calculateAvailDate(LocalDate shipDate, OrderCreationRequestObject request) {
        List<LocalDate> holidays = prestonHolidayRepository.findAllHolidayDates();
        int transitDays = numberOfTransitAndPadTransitDays(request);
        return calculateFutureBusinessDate(shipDate, holidays, transitDays);
    }

    private String calculateReplacementVendorCode(List<SpecialProduct> specialProduct) {
        return maxManufacturingDays(specialProduct).map(VendorProductDetails::getReplacementManufacturingCode).orElse(null);
    }

    private String calculateManufacturingVendorCode(List<SpecialProduct> specialProduct) {
        return maxManufacturingDays(specialProduct).map(VendorProductDetails::getManufacturingCode).orElse(null);
    }

    private int numberOfTransitAndPadTransitDays(OrderCreationRequestObject request) {
        int transitDays = zipCodeTransitMappingRepository.findByZipCode(request.getZipCode()).getTransitDays();
        if (storeRepository.findIsPadTransitByStoreCode(request.getStoreCode().toUpperCase())) {
            transitDays++;
        }
        return transitDays;
    }

    private boolean isBusinessDay(LocalDate date, List<LocalDate> holidays) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY && !holidays.contains(date);
    }

    private LocalDate calculateFutureBusinessDate(LocalDate startDate, List<LocalDate> holidays, long days) {
        int addedDays = 0;
        LocalDate resultDate = startDate;
        while (addedDays < days) {
            resultDate = resultDate.plusDays(1);
            if (isBusinessDay(resultDate, holidays)) {
                addedDays++;
            }
        }
        return resultDate;
    }

    private LocalDate calculatePastBusinessDate(LocalDate startDate, List<LocalDate> holidays, long days) {
        int addedDays = 0;
        LocalDate resultDate = startDate;
        while (addedDays < days) {
            resultDate = resultDate.minusDays(1);
            if (isBusinessDay(resultDate, holidays)) {
                addedDays++;
            }
        }
        return resultDate;
    }

    private Optional<VendorProductDetails> maxManufacturingDays(List<SpecialProduct> specialProduct) {
        List<VendorProductDetails> productDetailsList;
        List<Integer> specialProductList = specialProduct.stream().map(SpecialProduct::getId).toList();
        if (specialProductList.isEmpty()) {
            productDetailsList = vendorRepository.findByManufacturingCode("VC20");
        } else {
            productDetailsList = vendorRepository.findAllById(specialProductList);
        }
        return productDetailsList.stream()
                .max(Comparator.comparing(VendorProductDetails::getManufacturingTotalBizDays));
    }


}