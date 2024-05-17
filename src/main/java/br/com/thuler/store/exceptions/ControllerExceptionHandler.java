package br.com.thuler.store.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardException> notFound(NotFoundException e, HttpServletRequest req){
        String exception = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardException excep = new StandardException(Instant.now(), status.value(), exception, e.getMessage(), req.getRequestURI());
        return ResponseEntity.status(status).body(excep);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardException> database(DatabaseException e, HttpServletRequest req){
        String exception = "Database exception";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardException excep = new StandardException(Instant.now(), status.value(), exception, e.getMessage(), req.getRequestURI());
        return ResponseEntity.status(status).body(excep);
    }

}
