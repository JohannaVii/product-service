package se.iths.johanna.productservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * Hanterar fel i Product Service
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Hanterar valideringsfel
     *
     * @param ex innehåller information om valideringsfelet
     * @return ett felsvar med valideringsfelen
     */
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

    /**
     * Hanterar övriga fel
     *
     * @param ex innehåller information om felet
     * @return ett svar med felmeddelandet
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {

        // - Skapar felmeddelande
        ErrorResponse response = new ErrorResponse(ex.getMessage(), List.of());

        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Hanterar när en produkt inte hittas
     *
     * @param ex innehåller information om felet
     * @return ett svar med status 404
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException ex) {

        return ResponseEntity.notFound().build();
    }

    /**
     * Hanterar när lagersaldot inte räcker
     *
     * @param ex innehåller information om felet
     * @return ett svar med felmeddelandet
     */
    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientStock(InsufficientStockException ex) {

        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage(), List.of()));
    }
}
