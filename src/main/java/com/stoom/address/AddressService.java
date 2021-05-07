package com.stoom.address;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.stoom.exceptions.AddressNotFoundException;
import com.stoom.validation.AddressValidator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressService {

    final AddressRepository addressRepository;

    final AddressMapper addressMapper;

    final AddressValidator addressValidator;

    final static String key = "AIzaSyCj0cY2yEvVfYhAaTz3-P2MW-YRKmhz5Uw";

    public Address addAddress(AddressRequestData addressRequestData) {
        var address = addressMapper.map(addressRequestData);

        generateLatitudeLongitude(address);
        addressValidator.validate(address);
        return addressRepository.saveAndFlush(address);
    }

    public void deleteAddress(Long id) {
        var optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            addressRepository.delete(optionalAddress.get());
        } else {
            throw new AddressNotFoundException("Address not found");
        }
    }

    public Address updateAddress(Address address) {
        var optionalAddress = addressRepository.findById(address.getId());

        if(optionalAddress.isPresent()){
            generateLatitudeLongitude(address);
            addressValidator.validate(address);
            return addressRepository.save(address);
        } else {
            throw new AddressNotFoundException("Address not found");
        }
    }

    public Address findAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(() ->{
            throw new AddressNotFoundException("Address not found");
        });
    }

    private String mountAddress(Address address){
        return StringUtils.join(address.getStreetName(),", ",
                address.getNumber(),", ",
                address.getNeighbourhood(),", ",
                address.getCity(),", ",
                address.getState(),", ",
                address.getCountry()
        );
    }

    @SneakyThrows
    private void generateLatitudeLongitude(Address address){
        if(address.getLatitude() == null || address.getLongitude() == null) {
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(key)
                    .build();
            GeocodingResult[] results = GeocodingApi.geocode(context,
                    mountAddress(address)).await();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            address.setLatitude(Double.valueOf(gson.toJson(results[0].geometry.location.lat)));
            address.setLongitude(Double.valueOf(gson.toJson(results[0].geometry.location.lng)));
        }
    }
}
