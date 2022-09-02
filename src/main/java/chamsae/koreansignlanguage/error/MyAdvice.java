package chamsae.koreansignlanguage.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity customHandler(RuntimeException e) {
        return ResponseEntity.badRequest().build();
    }

}
