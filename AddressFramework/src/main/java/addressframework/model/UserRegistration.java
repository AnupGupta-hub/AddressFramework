package addressframework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_reg")
public class UserRegistration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int userId;

    @Column(name = "device_type")
    private String regDeviceType;

    @Column(name = "device_value")
    private String regDeviceValue;

    @Column(name = "role")
    private String role;

    @Column(name = "status")
    private int status;

    @Column(name = "registration")
    private Timestamp registration;

    @Column(name = "security_question_1")
    private String securityQuestion1;

    @Column(name = "security_answer_1")
    private String securityAnswer1;

    @Column(name = "security_question_2")
    private String securityQuestion2;

    @Column(name = "security_answer_2")
    private String securityAnswer2;

}
