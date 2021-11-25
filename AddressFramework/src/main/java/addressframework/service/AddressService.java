package addressframework.service;

import org.jose4j.json.internal.json_simple.JSONObject;
import addressframework.model.dto.AddressRequestDTO;
import addressframework.model.dto.AddressResponseDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface AddressService {
    Map<?, ?> addAddress(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AddressRequestDTO addressRequestDTO);

    List<AddressResponseDTO> fetchSelfAddress(HttpServletRequest request, HttpServletResponse httpResponse, String address_type, int self_addresss);

    List<AddressResponseDTO> fetchAllAddress(HttpServletRequest request, HttpServletResponse httpResponse);

    List<AddressResponseDTO> fetchAllSelfAddress(HttpServletRequest request, HttpServletResponse httpResponse, String address_type);

    Map<String, String> deleteAddress(HttpServletRequest request, HttpServletResponse httpResponse, Long addressId);

    Map<?, ?> updateAddress(HttpServletRequest request, HttpServletResponse httpResponse, Long addressId, AddressRequestDTO addressRequestDTO);

    Map<String, String> forgotDevice( AddressRequestDTO requestDTO);

    Map<String, String> validateStateZipCombination(AddressRequestDTO requestDTO);

    JSONObject stateList();
}
