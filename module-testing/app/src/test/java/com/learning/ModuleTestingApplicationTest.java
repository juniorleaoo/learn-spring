package com.learning;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
@ActiveProfiles("test")
public class ModuleTestingApplicationTest {

    @Test
    void test(){

    }

}
