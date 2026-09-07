package com.example.mspassportinspectorgateway.exception;

import com.example.mspassportinspectorgateway.model.ErrorResponseDto;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleBadCredentials(BadCredentialsException e) {
        log.warn("Login failed: {}", e.getMessage());
        return build(HttpStatus.UNAUTHORIZED, "Invalid username or password");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HandlerMethodValidationException.class})
    public ResponseEntity<ErrorResponseDto> handleValidation(Exception ignored) {
        return build(HttpStatus.BAD_REQUEST, "Request validation failed");
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponseDto> handleDownstreamFailure(FeignException e) {
        log.error("Downstream call to ms-passport-inspector failed: status={}, message={}",
                e.status(), e.getMessage());

        if (e.status() >= 400 && e.status() < 500) {
            return build(HttpStatus.BAD_GATEWAY, "Downstream service rejected the request");
        }
        return build(HttpStatus.SERVICE_UNAVAILABLE, "Downstream service is unavailable");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleUnexpected(Exception e) {
        log.error("Unhandled exception", e);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
    }

    private ResponseEntity<ErrorResponseDto> build(HttpStatus status, String message) {
        ErrorResponseDto body = ErrorResponseDto.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .traceId(MDC.get("traceId"))
                .build();
        return ResponseEntity.status(status).body(body);
    }
}