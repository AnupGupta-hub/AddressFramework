package addressframework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import addressframework.exception.dto.ErrorGenericResponse;
import addressframework.exception.dto.InvalidRequestException;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidRequestException.class})
    public ResponseEntity<ErrorGenericResponse> invalidRequestException(InvalidRequestException exception) {
        String methodName = "invalidRequestException";
        ErrorGenericResponse response = new ErrorGenericResponse();
        response.setMessage(exception.getErrorMsg());
        response.setCode(exception.getErrorCode());
        response.setKey(exception.getErrorField());
        return new ResponseEntity<ErrorGenericResponse>(response, exception.getHttpStatus());
    }
}
