package com.swu.lease.web.admin.service.impl;

import com.swu.lease.model.entity.ApartmentFacility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ApartmentFacilityServiceImplTest {
    @Autowired
    private ApartmentFacilityServiceImpl apartmentFacilityServiceImpl;
    @Test
    public void test(){
        ApartmentFacility byId = apartmentFacilityServiceImpl.getById("1");
        System.out.println(byId);
    }
}