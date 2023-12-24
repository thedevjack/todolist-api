package api.projeto.todolist.exception.handler;


import api.projeto.todolist.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @ExceptionHandler(value = {NotFoundException.class})
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(RuntimeException exception, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .error(HttpStatus.NOT_FOUND.name())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public final ResponseEntity<ExceptionResponse> handlePerfilExceptions(RuntimeException exception, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .error(HttpStatus.BAD_REQUEST.name())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ConflictException.class})
    public final ResponseEntity<ExceptionResponse> handleConflictExceptions(RuntimeException exception, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .message(exception.getMessage())
                .error(HttpStatus.CONFLICT.name())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public final ResponseEntity<?> handleValidateExceptions(ConstraintViolationException exception, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .error(HttpStatus.BAD_REQUEST.name())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .message(ex.getBindingResult().getFieldErrors().stream().map(ValidationField::new).toList().toString())
                .error(HttpStatus.valueOf(status.value()).name())
                .path(httpServletRequest.getRequestURI())
                .build();
        System.out.println(ex);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.valueOf(status.value()));
//	  return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }
}
