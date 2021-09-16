package pl.pcz.gumtree.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.pcz.gumtree.exceptions.GumtreeCopyException;
import pl.pcz.gumtree.exceptions.GumtreeErrorResponse;

import java.util.Optional;


@RestControllerAdvice
public class ExceptionHandlerController {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<GumtreeErrorResponse> handleGeneralError(Exception exception) {
//        return ResponseEntity.of(
//                Optional.of(
//                        GumtreeErrorResponse.builder().message("niespodziewany błąd").build()
//                )
//        );
//    }

    @ExceptionHandler(GumtreeCopyException.class)
    public ResponseEntity<GumtreeErrorResponse> handleGumtryException(GumtreeCopyException exception) {
        return ResponseEntity.of
                (Optional.of
                        (GumtreeErrorResponse.builder().message(exception.getMessage()).build())
                );
    }

}
