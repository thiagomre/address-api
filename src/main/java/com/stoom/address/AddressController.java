package com.stoom.address;

import com.stoom.config.BaseRestController;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("address")
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressController extends BaseRestController {

    final AddressService addressService;

    @ApiOperation("Add a address")
    @PostMapping(value = "addAddress")
    public ResponseEntity<Address> addAddress(@RequestBody AddressRequestData address) {
        var result = addressService.addAddress(address);
      return ResponseEntity.created(locationByEntity(result.getId())).body(result);
    }

    @ApiOperation("Delete a address")
    @DeleteMapping(value = "deleteAddress")
    public ResponseEntity<Void> deleteAddress(@RequestParam Long addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Update a address")
    @PutMapping(value = "updateAddress")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address) {
        var result = addressService.updateAddress(address);
        return ResponseEntity.ok(result);
    }

    @ApiOperation("Get a address")
    @GetMapping(value = "getAddress")
    public ResponseEntity<Address> getAddress(@RequestParam Long addressId) {
        return ResponseEntity.ok(addressService.findAddressById(addressId));
    }

}
