package by.morunov.socialnetwork.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Alex Morunov
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RegistrationException.class)
    protected ResponseEntity<CustomException> handleThereIsNoSuchUserException() {
        return new ResponseEntity<>(new CustomException("This email already taken. Choose other email."), HttpStatus.CONFLICT);
    }


    @Data
    @AllArgsConstructor
    private static class CustomException {
        private String message;
    }
}
