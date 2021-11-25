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
@Table(name = "user_activity")
public class UserActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "activity_id")
    private int activityId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "secret")
    private int secret;

    @Column(name = "session_start_time")
    private Timestamp sessionStartTime;

    @Column(name = "session_end_time")
    private Timestamp sessionEndTime;

    @Column(name = "session_id")
    private String sessionId;

}
