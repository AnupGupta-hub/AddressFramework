package addressframework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import addressframework.model.UserRegistration;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Integer> {
	UserRegistration findByRegDeviceValue(String deviceValue);
	UserRegistration findByUserId(int userId);
}
