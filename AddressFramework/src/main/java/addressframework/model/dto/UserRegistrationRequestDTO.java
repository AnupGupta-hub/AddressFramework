package addressframework.model.dto;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String regDeviceType;

    private String regDeviceValue;

    private String firstName;

    private String lastName;
}
