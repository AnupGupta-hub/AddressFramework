package addressframework.exception.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvalidRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus;
    private String errorField;
    private String errorMsg;
    private String errorCode;
}
