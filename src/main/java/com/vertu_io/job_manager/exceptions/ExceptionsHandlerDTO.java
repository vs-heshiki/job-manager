package com.vertu_io.job_manager.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionsHandlerDTO {
    private String message;
    private String field;
}
