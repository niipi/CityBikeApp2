package eu.piiroinen.citybike2.exception;

import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice(annotations = Controller.class)
class GlobalControllerExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleMissingElementException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No such element, please check id parameter");
    }

    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public ResponseEntity<String> handleDataAccessException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("No access to database with these credentials for this operation.");
    }
}

