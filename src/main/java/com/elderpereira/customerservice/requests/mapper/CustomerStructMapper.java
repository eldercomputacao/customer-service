package com.elderpereira.customerservice.requests.mapper;

import com.elderpereira.customerservice.domain.Customer;
import com.elderpereira.customerservice.requests.CustomerPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerStructMapper {

    CustomerStructMapper MAPPER = Mappers.getMapper( CustomerStructMapper.class );

    @Mappings({
            @Mapping(source = "country", target = "address.country"),
            @Mapping(source = "street", target = "address.street"),
            @Mapping(source = "district", target = "address.district"),
            @Mapping(source = "number", target = "address.number"),
            @Mapping(source = "complement", target = "address.complement"),
            @Mapping(source = "postalCode", target = "address.postalCode"),
            @Mapping(source = "city", target = "address.city"),
            @Mapping(source = "state", target = "address.state"),

    })
    Customer toCustomer(CustomerPostRequestBody customerPostRequestBody);


}
