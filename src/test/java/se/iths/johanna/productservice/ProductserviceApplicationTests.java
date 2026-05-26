package se.iths.johanna.productservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import se.iths.johanna.productservice.config.SecurityConfig;

@SpringBootTest
@ImportAutoConfiguration(exclude = SecurityConfig.class)
class ProductserviceApplicationTests {

    @Test
    void contextLoads() {
    }

}
