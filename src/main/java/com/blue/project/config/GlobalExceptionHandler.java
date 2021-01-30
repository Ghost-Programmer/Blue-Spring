package com.blue.project.config;

import com.blue.project.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    // handle the 401 requests
    @ExceptionHandler({UnauthorizedUserException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorResponse unAuthorizedHandler(Exception e) {
        return getResponse(e, HttpStatus.UNAUTHORIZED);
    }

    // handle the 401 requests
    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse badRequestHandler(Exception e) {
        return getResponse(e, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse getResponse(Exception e, HttpStatus status) {
        this.logger.error("Error on API", e);

        ErrorResponse error = new ErrorResponse(e);
        error.setCode(status);
        return error;
    }
}
