package com.stoom.address;

import com.stoom.exceptions.AddressNotFoundException;
import com.stoom.validation.AddressValidator;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = AddressService.class)
public class AddressServiceTest {

    @InjectMocks
    AddressService addressService;

    @Mock
    AddressRepository addressRepository;

    @Mock
    AddressMapper addressMapper;

    @Mock
    AddressValidator addressValidator;

    @Test
    public void addAddress(){
        var address = FixtureAddress.fixtureAddressComplete();
        var addressRequestData = FixtureAddress.fixtureAddressRequestDataComplete();

        Mockito.when(addressMapper.map(addressRequestData)).thenReturn(address);
        Mockito.when(addressRepository.saveAndFlush(address)).thenReturn(address);

        Address response =  addressService.addAddress(addressRequestData);

        Mockito.verify(addressRepository).saveAndFlush(address);

        assertEquals(address, response);
    }

    @Test(expected = ConstraintViolationException.class)
    public void addAddressWithoutRequiredFields(){
        var address = FixtureAddress.fixtureAddressWithoutStreetName();
        var addressRequestData = FixtureAddress.fixtureAddressRequestDataWithoutStreetName();

        Mockito.when(addressMapper.map(addressRequestData)).thenReturn(address);
        Mockito.doThrow(ConstraintViolationException.class).when(addressValidator).validate(address);
        addressService.addAddress(addressRequestData);
    }

    @Test
    public void deleteAddress(){
        var address = FixtureAddress.fixtureAddressComplete();
        address.setId(1L);
        Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        addressService.deleteAddress(address.getId());

        Mockito.verify(addressRepository).delete(address);
    }

    @Test(expected = AddressNotFoundException.class)
    public void deleteAddressNotFoundException(){
        var address = FixtureAddress.fixtureAddressComplete();
        addressService.deleteAddress(address.getId());
    }

    @Test
    public void updateAddress(){
        var address = FixtureAddress.fixtureAddressComplete();
        address.setId(1L);
        Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        Mockito.when(addressRepository.save(address)).thenReturn(address);

        Address response =  addressService.updateAddress(address);

        Mockito.verify(addressRepository).save(address);

        assertEquals(address, response);
    }

    @Test(expected = AddressNotFoundException.class)
    public void updateAddressNotFoundException(){
        var address = FixtureAddress.fixtureAddressComplete();
        addressService.updateAddress(address);
    }

    @Test
    public void findAddressById(){
        var address = FixtureAddress.fixtureAddressComplete();
        address.setId(1L);
        Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        addressService.findAddressById(address.getId());
    }

    @Test(expected = AddressNotFoundException.class)
    public void findAddressByIdNotFoundException(){
        var address = FixtureAddress.fixtureAddressComplete();
        addressService.findAddressById(address.getId());
    }

}
