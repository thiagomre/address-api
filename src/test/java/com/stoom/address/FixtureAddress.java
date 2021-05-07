package com.stoom.address;

public class FixtureAddress {
    
    public static Address fixtureAddressComplete(){
        return Address.builder()
                .streetName("Alameda José de Oliveira Guimarães")
                .number(1148)
                .neighbourhood( "Jardim Holanda")
                .city("Uberlândia")
                .state("Minas Gerais")
                .country("Brasil")
                .zipcode("38412-324")
                .latitude( -18.9569879)
                .longitude( -48.3214029)
                .build();
    }

    public static AddressRequestData fixtureAddressRequestDataComplete(){
        return AddressRequestData.builder()
                .streetName("Alameda José de Oliveira Guimarães")
                .number(1148)
                .neighbourhood( "Jardim Holanda")
                .city("Uberlândia")
                .state("Minas Gerais")
                .country("Brasil")
                .zipcode("38412-324")
                .latitude( -18.9569879)
                .longitude( -48.3214029)
                .build();
    }

    public static Address fixtureAddressWithoutStreetName(){
        return Address.builder()
                .number(1148)
                .neighbourhood( "Jardim Holanda")
                .city("Uberlândia")
                .state("Minas Gerais")
                .country("Brasil")
                .zipcode("38412-324")
                .latitude( -18.9569879)
                .longitude( -48.3214029)
                .build();
    }

    public static AddressRequestData fixtureAddressRequestDataWithoutStreetName(){
        return AddressRequestData.builder()
                .number(1148)
                .neighbourhood( "Jardim Holanda")
                .city("Uberlândia")
                .state("Minas Gerais")
                .country("Brasil")
                .zipcode("38412-324")
                .latitude( -18.9569879)
                .longitude( -48.3214029)
                .build();
    }
}
