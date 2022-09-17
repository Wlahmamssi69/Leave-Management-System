package com.pfa.LeaveManagementSystem.handler;
import com.pfa.LeaveManagementSystem.exception.EntityNotFoundException;
import com.pfa.LeaveManagementSystem.exception.InvalidEntityException;
import com.pfa.LeaveManagementSystem.exception.InvalidOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Error> handleException(EntityNotFoundException exception, WebRequest webrequest) {
        final HttpStatus notFound = HttpStatus.NOT_FOUND;
          final  Error error = Error.builder()
                .code(exception.getErrorCode())
                .httpCode(notFound.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(error,notFound);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<Error> handleException(InvalidEntityException exception,WebRequest webrequest){

          final HttpStatus badRequest= HttpStatus.BAD_REQUEST;
          final  Error error = Error.builder()
                .code(exception.getErrorCode())
                .httpCode(badRequest.value())
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build();
          return new ResponseEntity<>(error,badRequest);
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<Error> handleException(InvalidOperationException exception, WebRequest webrequest) {
        final HttpStatus notFound = HttpStatus.BAD_REQUEST;
        final  Error error = Error.builder()
                .code(exception.getErrorCode())
                .httpCode(notFound.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(error,notFound);
    }
}
