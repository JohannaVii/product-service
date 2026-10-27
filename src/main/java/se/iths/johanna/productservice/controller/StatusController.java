package se.iths.johanna.productservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Visar status för ProductService
 */
@RestController
public class StatusController {

    /**
     * Kontrollerar att ProductService är igång
     *
     * @return meddelande om att tjänsten är igång
     */
    @GetMapping("/")
    public String status() {
        return "Product Service är igång!";
    }
}