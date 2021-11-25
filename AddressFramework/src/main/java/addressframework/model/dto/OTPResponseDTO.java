package addressframework.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OTPResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String msg;
    private int otp;
    private String device_value;
}

