package com.swu.lease.web.admin.mapper;

import com.swu.lease.model.entity.ApartmentFacility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ApartmentFacilityMapperTest {
    @Autowired
    private ApartmentFacilityMapper apartmentFacilityMapper;
    @Test
    void getListTest() {
        List<ApartmentFacility> apartmentFacilities = apartmentFacilityMapper.selectList(null);
        apartmentFacilities.forEach(System.out::println);;
    }
}