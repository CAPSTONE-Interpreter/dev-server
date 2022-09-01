package chamsae.koreansignlanguage.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity customHandler(Exception e) {
        return ResponseEntity.badRequest().build();
    }

}
