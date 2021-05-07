package com.stoom.address;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressRequestData {

    String streetName;

    Integer number;

    String complement;

    String neighbourhood;

    String city;

    String state;

    String country;

    String zipcode;

    Double latitude;

    Double longitude;
}
