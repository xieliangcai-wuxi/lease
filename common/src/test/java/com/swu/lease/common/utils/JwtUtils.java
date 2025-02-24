package com.swu.lease.common.utils;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtils {
    @Test
    public void test() {
        System.out.println(utils.JwtUtils.createToken(1L,"admin"));
    }
}
