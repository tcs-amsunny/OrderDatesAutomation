package com.containerstore.prestonintegrations.ordercreation.OrderApplication.controller;


import com.containerstore.prestonintegrations.ordercreation.OrderApplication.Entity.VendorProductDetails;
import com.containerstore.prestonintegrations.ordercreation.OrderApplication.dto.OrderCreationRequestObject;
import com.containerstore.prestonintegrations.ordercreation.OrderApplication.dto.OrderCreationResponseObject;
import com.containerstore.prestonintegrations.ordercreation.OrderApplication.service.OderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendorProductDetailsController {

    @Autowired
    private OderService orderService;

    @GetMapping("/leadDates")
    public OrderCreationResponseObject calculateDates(@RequestBody OrderCreationRequestObject request) {
        if (request.getInstallationDate() != null) {
            return orderService.backwardCalculator(request);
        } else if (request.getShipDate() != null) {
            return orderService.shipDateCalculatorOnly(request);
        }
        return orderService.forwardDatesCalculator(request);
    }

}