package addressframework.service.impl;


import org.jose4j.json.internal.json_simple.JSONArray;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.json.internal.json_simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import addressframework.exception.dto.InvalidRequestException;
import addressframework.model.Address;
import addressframework.model.UserRegistration;
import addressframework.model.dto.AddressRequestDTO;
import addressframework.model.dto.AddressResponseDTO;
import addressframework.repository.AddressRepository;
import addressframework.repository.UserRegistrationRepository;
import addressframework.service.AddressService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.util.*;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    private SessionServiceImpl sessionServiceImpl;

    @Autowired
    private AddressRepository addressRepository;


    public Map<?, ?> addAddress(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                AddressRequestDTO addressRequestDTO) {
        int userId= sessionServiceImpl.validateSession(httpServletRequest, httpServletResponse);
        Map<String, String> responseMap = new HashMap<>();
        UserRegistration userRegistration = userRegistrationRepository.findByUserId(userId);
        if (userRegistration == null) {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "addAddress",
                    "Device is not registered, I need to register device first", "ADD-001");
        }
        Address address = new Address();
        address.setFirstName(addressRequestDTO.getFirstName());
        address.setLastName(addressRequestDTO.getLastName());
        address.setAddress1(addressRequestDTO.getAddress1());
        address.setAddress2(addressRequestDTO.getAddress2());
        address.setAddress3(addressRequestDTO.getAddress3());
        address.setAddressType(addressRequestDTO.getAddressType());
        address.setCity(addressRequestDTO.getCity());
        address.setState(addressRequestDTO.getState());
        address.setStateCode(addressRequestDTO.getStateCode());
        address.setZipCode(addressRequestDTO.getZipCode());
        address.setUserId(Integer.valueOf(userId));
        address.setSelfAddress(addressRequestDTO.getSelfAddress());
        address = addressRepository.save(address);
        responseMap.put("status", "success");
        responseMap.put("addressType", address.getAddressType());
        responseMap.put("addressId", address.getAddressId().toString());
        return responseMap;
    }

    public List<AddressResponseDTO> fetchSelfAddress(HttpServletRequest request, HttpServletResponse httpResponse, String address_type, int self_address) {
       int userId= sessionServiceImpl.validateSession(request, httpResponse);
        List<AddressResponseDTO> addressResponseDTOS = new ArrayList<AddressResponseDTO>();
        List<Address> addresses = addressRepository.findByAddressTypeAndSelfAddress(address_type, self_address);
        if (addresses == null) {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, address_type,
                    "No Self Address Present", "ADD-002");
        }
        for (Address address : addresses
        ) {
            AddressResponseDTO addressResponseDTO = new AddressResponseDTO();
            addressResponseDTO.setAddressId(address.getAddressId());
            addressResponseDTO.setAddressType(address.getAddressType());
            addressResponseDTO.setSelfAddress(address.getSelfAddress());
            addressResponseDTO.setFirstName(address.getFirstName());
            addressResponseDTO.setLastName(address.getLastName());
            addressResponseDTO.setAddress1(address.getAddress1());
            addressResponseDTO.setAddress2(address.getAddress2());
            addressResponseDTO.setAddress3(address.getAddress3());
            addressResponseDTO.setCity(address.getCity());
            addressResponseDTO.setState(address.getState());
            addressResponseDTO.setStateCode(address.getStateCode());
            addressResponseDTO.setZipCode(address.getZipCode());
            addressResponseDTOS.add(addressResponseDTO);
        }

        return addressResponseDTOS;
    }

    public List<AddressResponseDTO> fetchAllAddress(HttpServletRequest request, HttpServletResponse httpResponse) {
        int userId=sessionServiceImpl.validateSession(request, httpResponse);
        List<AddressResponseDTO> addressResponseDTOS = new ArrayList<AddressResponseDTO>();
        List<Address> addresses = addressRepository.findByUserId(userId);
        if (addresses == null) {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "address",
                    "No Address Present", "ADD-003");
        }
        for (Address address : addresses
        ) {
            AddressResponseDTO addressResponseDTO = new AddressResponseDTO();
            addressResponseDTO.setAddressId(address.getAddressId());
            addressResponseDTO.setAddressType(address.getAddressType());
            addressResponseDTO.setSelfAddress(address.getSelfAddress());
            addressResponseDTO.setFirstName(address.getFirstName());
            addressResponseDTO.setLastName(address.getLastName());
            addressResponseDTO.setAddress1(address.getAddress1());
            addressResponseDTO.setAddress2(address.getAddress2());
            addressResponseDTO.setAddress3(address.getAddress3());
            addressResponseDTO.setCity(address.getCity());
            addressResponseDTO.setState(address.getState());
            addressResponseDTO.setStateCode(address.getStateCode());
            addressResponseDTO.setZipCode(address.getZipCode());
            addressResponseDTOS.add(addressResponseDTO);
        }

        return addressResponseDTOS;
    }

    public List<AddressResponseDTO> fetchAllSelfAddress(HttpServletRequest request, HttpServletResponse httpResponse, String address_type) {
       int userId= sessionServiceImpl.validateSession(request, httpResponse);
        List<AddressResponseDTO> addressResponseDTOS = new ArrayList<AddressResponseDTO>();
        List<Address> addresses = addressRepository.findByUserIdAndAddressType(userId, address_type);
        if (addresses == null) {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "address",
                    "No Self Address Present", "ADD-004");
        }
        for (Address address : addresses
        ) {
            AddressResponseDTO addressResponseDTO = new AddressResponseDTO();
            addressResponseDTO.setAddressId(address.getAddressId());
            addressResponseDTO.setAddressType(address.getAddressType());
            addressResponseDTO.setSelfAddress(address.getSelfAddress());
            addressResponseDTO.setFirstName(address.getFirstName());
            addressResponseDTO.setLastName(address.getLastName());
            addressResponseDTO.setAddress1(address.getAddress1());
            addressResponseDTO.setAddress2(address.getAddress2());
            addressResponseDTO.setAddress3(address.getAddress3());
            addressResponseDTO.setCity(address.getCity());
            addressResponseDTO.setState(address.getState());
            addressResponseDTO.setStateCode(address.getStateCode());
            addressResponseDTO.setStateCode(address.getStateCode());
            addressResponseDTO.setZipCode(address.getZipCode());
            addressResponseDTOS.add(addressResponseDTO);
        }

        return addressResponseDTOS;
    }

    public Map<String, String> deleteAddress(HttpServletRequest request, HttpServletResponse httpResponse, Long addressId) {
       int userId= sessionServiceImpl.validateSession(request, httpResponse);
        Map<String, String> responseMap = new HashMap<>();
        addressRepository.deleteById(addressId);
        responseMap.put("status", "success");
        return responseMap;
    }

    public Map<?, ?> updateAddress(HttpServletRequest request, HttpServletResponse httpResponse, Long addressId, AddressRequestDTO addressRequestDTO) {
       int userId= sessionServiceImpl.validateSession(request, httpResponse);
        Map<String, String> responseMap = new HashMap<>();
        Optional<Address> address = addressRepository.findById(addressId);
        if (address == null) {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, addressId.toString(),
                    "Invalid Address to Update", "ADD-004");
        }
        if (addressRequestDTO.getAddress1() != null) address.get().setAddress1(addressRequestDTO.getAddress1());
        if (addressRequestDTO.getAddress2() != null) address.get().setAddress2(addressRequestDTO.getAddress2());
        if (addressRequestDTO.getAddress3() != null) address.get().setAddress2(addressRequestDTO.getAddress3());
        if (addressRequestDTO.getAddressType() != null)
            address.get().setAddressType(addressRequestDTO.getAddressType());
        if (addressRequestDTO.getCity() != null) address.get().setCity(addressRequestDTO.getCity());
        if (addressRequestDTO.getState() != null) address.get().setState(addressRequestDTO.getState());
        if (addressRequestDTO.getStateCode() != null) address.get().setState(addressRequestDTO.getStateCode());
        if (addressRequestDTO.getZipCode() != null) address.get().setZipCode(addressRequestDTO.getZipCode());
        address.get().setSelfAddress(addressRequestDTO.getSelfAddress());
        addressRepository.save(address.get());
        responseMap.put("status", "success");
        responseMap.put("addressType", address.get().getAddressType());
        responseMap.put("addressId", address.get().getAddressId().toString());
        return responseMap;
    }

    public Map<String, String> forgotDevice(AddressRequestDTO requestDTO) {
        Map<String, String> respMap = new HashMap<>();

        List<Address> addresses = addressRepository.findByFirstNameAndLastNameAndStateAndCityAndSelfAddress
                (requestDTO.getFirstName(), requestDTO.getLastName(), requestDTO.getState(), requestDTO.getCity(), 1);
        if (addresses != null && addresses.size() > 0) {
            for (Address address : addresses
            ) {
                int userId = address.getUserId();
                UserRegistration userRegistration = userRegistrationRepository.findByUserId(userId);
                respMap.put("device_value", userRegistration.getRegDeviceValue());
                break;
            }
        } else {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "address",
                    "Address not present", "ADD-009");
        }
        return respMap;
    }

    public Map<String, String> validateStateZipCombination(AddressRequestDTO requestDTO) {
        boolean zipcodePresent = false;
        String path = System.getProperty("user.dir") + "//statezip.json";
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(path)) {

            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObj = (JSONObject) obj;

            Iterator keys = jsonObj.keySet().iterator();
            while (keys.hasNext()) {
                String currentDynamicKey = (String) keys.next();
                if (currentDynamicKey.equalsIgnoreCase(requestDTO.getState())) {
                    JSONArray zipCodeArray = (JSONArray) jsonObj.get(currentDynamicKey);
                    Iterator<String> zipcodes = zipCodeArray.iterator();
                    while ((zipcodes.hasNext())) {
                        if (zipcodes.next().equalsIgnoreCase(requestDTO.getZipCode())) {
                            zipcodePresent = true;
                            break;
                        }
                    }
                }
            }

        } catch (Exception e) {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, requestDTO.getState(),
                    "Exception occurred in validation", "ADD-010");
        }
        if (zipcodePresent) {
            Map<String, String> respMap = new HashMap<>();
            respMap.put("msg", "validationSuccess");
            return respMap;
        } else {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, requestDTO.getState(),
                    "State ZipCode validation failed", "ADD-009");

        }
    }

    public JSONObject stateList() {
        String path = System.getProperty("user.dir") + "//statecode.json";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = null;
        try (FileReader reader = new FileReader(path)) {

            //Read JSON file
            Object obj = jsonParser.parse(reader);
            jsonObj = (JSONObject) obj;
        } catch (Exception e) {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "statelist",
                    "Exception occurred in getting state list", "ADD-010");
        }
        return jsonObj;
    }
}