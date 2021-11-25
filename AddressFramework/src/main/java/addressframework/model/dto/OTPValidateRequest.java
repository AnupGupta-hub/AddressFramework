package addressframework.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OTPValidateRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private int otp;
    private String device_value;
}
