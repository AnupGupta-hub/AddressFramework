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
public class AddressResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;

    private String lastName;

    private Long addressId;

    private String addressType;

    private int selfAddress;

    private String address1;

    private String address2;

    private String address3;

	private String state;

    private String stateCode;

    private String city;

    private String zipCode;

}
