package addressframework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import addressframework.model.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByAddressTypeAndSelfAddress(String AddressType, int selfAddress);

    List<Address> findByUserId(int user_id);

    List<Address> findByUserIdAndAddressType(int user_id, String addressType);

    List<Address> findByFirstNameAndLastNameAndStateAndCityAndSelfAddress(String firstName, String lastName, String state, String city, int selfAddress);
}
