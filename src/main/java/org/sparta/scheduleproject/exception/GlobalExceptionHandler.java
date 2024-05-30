package org.sparta.scheduleproject.exception;

import org.sparta.scheduleproject.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({ InvalidPasswordException.class,
            InvalidTokenException.class,
            UnauthorizedException.class,
            DuplicateUsernameException.class,
            LoginFailedException.class})
//    protected ResponseEntity handleCustomException(InvalidPasswordException ex) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
//    }
    public ResponseEntity<ErrorDto> handleCustomExceptions(RuntimeException ex) {
        int statusCode;
        if (ex instanceof InvalidTokenException) {
            statusCode = ((InvalidTokenException) ex).getStatusCode();
        } else if (ex instanceof UnauthorizedException) {
            statusCode = ((UnauthorizedException) ex).getStatusCode();
        } else if (ex instanceof DuplicateUsernameException) {
            statusCode = ((DuplicateUsernameException) ex).getStatusCode();
        } else if (ex instanceof LoginFailedException) {
            statusCode = ((LoginFailedException) ex).getStatusCode();
        } else {
            statusCode = HttpStatus.BAD_REQUEST.value(); // 기본 상태 코드
        }

        ErrorDto errorDto = new ErrorDto(ex.getMessage(), String.valueOf(statusCode));
        return new ResponseEntity<>(errorDto, HttpStatus.valueOf(statusCode));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGeneralException(Exception ex) {
        ErrorDto errorDto = new ErrorDto("서버 오류가 발생했습니다", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


