package com.project.blog.api;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import com.project.blog.cmmn.enums.Error;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice(annotations = ResponseBody.class)
public class ApiExceptionAdvice {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Object>> customExceptionHandler(HttpServletRequest request, final ApiException e) {
    	ApiResponse<Object> apiResponse = ApiResponse.<Object>builder()
    			.status(e.getApiStatus().getStatusCode())
    			.message(e.getApiStatus().getMessage())
    			.errors(null)
    			.build();

        return ResponseEntity
                .ok(apiResponse);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> dataIntegrityViolationExceptionHandler(HttpServletRequest request, final DataIntegrityViolationException dataIntegrityViolationException) {
    	ApiResponse<Object> apiResponse = ApiResponse.<Object>builder()
    			.status(Error.REQUIRED_ERROR.getStatusCode())
    			.message(Error.REQUIRED_ERROR.getMessage())
    			.errors(dataIntegrityViolationException.getMessage())
    			.build();

        return ResponseEntity
                .ok(apiResponse);
    }
    
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<Object>> notFoundExceptionHandler(HttpServletRequest request, final NoResourceFoundException noResourceFoundException) {
    	ApiResponse<Object> apiResponse = ApiResponse.<Object>builder()
    			.status(Error.NOT_FOUND.getStatusCode())
    			.message(Error.NOT_FOUND.getMessage())
    			.errors(noResourceFoundException.getMessage())
    			.build();

        return ResponseEntity
                .ok(apiResponse);
    }
}