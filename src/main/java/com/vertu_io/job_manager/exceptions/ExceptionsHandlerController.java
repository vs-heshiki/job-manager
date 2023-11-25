package com.vertu_io.job_manager.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionsHandlerController {

    private final MessageSource messageSource;

    public ExceptionsHandlerController(MessageSource message) {
        this.messageSource = message;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionsHandlerDTO>> handlerBadRequestValidation(MethodArgumentNotValidException e) {
        List<ExceptionsHandlerDTO> dto = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(err -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            ExceptionsHandlerDTO errors = new ExceptionsHandlerDTO(message, err.getField());
            dto.add(errors);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
