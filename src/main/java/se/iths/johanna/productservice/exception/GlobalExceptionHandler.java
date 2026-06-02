package se.iths.johanna.productservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Metod - Hanterar valideringsfel (VALID)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {

        // - Hämtar alla felmeddelanden
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        ErrorResponse response = new ErrorResponse("Valideringsfel", errors);

        return ResponseEntity.badRequest().body(response);
    }

    // Metod - Hanterar övriga fel
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {

        // - Skapar felmeddelande
        ErrorResponse response = new ErrorResponse(ex.getMessage(), List.of());

        return ResponseEntity.badRequest().body(response);
    }

    // Metod - Produkt hittades inte
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException ex) {

        return ResponseEntity.notFound().build();
    }

    // Metod - Inte tillräckligt lagersaldo
    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientStock(InsufficientStockException ex) {

        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage(), List.of()));
    }
}
