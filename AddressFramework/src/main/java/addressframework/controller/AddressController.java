package addressframework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import addressframework.model.dto.AddressRequestDTO;
import addressframework.service.AddressService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/v1/add-service")
@CrossOrigin("*")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @RequestMapping(value = "/address", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAddress(@RequestBody AddressRequestDTO addressRequestDTO, HttpServletRequest request,
                                        HttpServletResponse httpResponse) throws IOException {
        return new ResponseEntity<>(addressService.addAddress(request, httpResponse, addressRequestDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/address/{addressId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAddress(@PathVariable Long addressId, @RequestBody AddressRequestDTO addressRequestDTO, HttpServletRequest request,
                                           HttpServletResponse httpResponse) throws IOException {
        return new ResponseEntity<>(addressService.updateAddress(request, httpResponse, addressId, addressRequestDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/address/{addressId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAddress(@PathVariable Long addressId, HttpServletRequest request,
                                           HttpServletResponse httpResponse) throws IOException {
        return new ResponseEntity<>(addressService.deleteAddress(request, httpResponse, addressId), HttpStatus.OK);
    }

    @RequestMapping(value = "/address/{address_type}/{self_address}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> selfAddress(@PathVariable String address_type, @PathVariable int self_address, HttpServletRequest request,
                                         HttpServletResponse httpResponse) throws IOException {
        return new ResponseEntity<>(addressService.fetchSelfAddress(request, httpResponse, address_type, self_address), HttpStatus.OK);
    }

    @RequestMapping(value = "/address", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchAllAddress(HttpServletRequest request,
                                             HttpServletResponse httpResponse) throws IOException {
        return new ResponseEntity<>(addressService.fetchAllAddress(request, httpResponse), HttpStatus.OK);
    }

    @RequestMapping(value = "/address/{address_type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> selfAllAddress(@PathVariable String address_type, HttpServletRequest request,
                                            HttpServletResponse httpResponse) throws IOException {
        return new ResponseEntity<>(addressService.fetchAllSelfAddress(request, httpResponse, address_type), HttpStatus.OK);
    }

    @RequestMapping(value = "/validate/state/zip", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validateStateZip(@RequestBody AddressRequestDTO requestDTO,
                                          HttpServletRequest request, HttpServletResponse httpResponse) throws IOException {
        return new ResponseEntity<>(addressService.validateStateZipCombination(requestDTO), HttpStatus.OK);

    }

    @RequestMapping(value = "/state/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStateList(HttpServletRequest request, HttpServletResponse httpResponse) throws IOException {
        return new ResponseEntity<>(addressService.stateList(), HttpStatus.OK);

    }
}