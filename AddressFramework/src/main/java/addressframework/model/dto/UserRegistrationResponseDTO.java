package addressframework.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status;
    private String redirectURL;
    private String device_type;
    private String device_value;
}
