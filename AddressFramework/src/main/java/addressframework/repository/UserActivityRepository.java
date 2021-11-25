package addressframework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import addressframework.model.UserActivity;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {

	UserActivity findBySessionId(String sessionId);

	UserActivity findByUserId(int userId);
}
