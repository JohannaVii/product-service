package se.iths.johanna.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startar Product Service
 */
@SpringBootApplication
public class ProductserviceApplication {

    /**
     * Startar applikationen
     *
     * @param args argument som skickas med vid start
     */
    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }

}
