package addressframework.exception.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorGenericResponse {

    private String message;
    private String key;
    private String code;
}
