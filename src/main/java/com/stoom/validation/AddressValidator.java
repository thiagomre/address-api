package com.stoom.validation;

import com.stoom.address.Address;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AddressValidator {

    private static final String MESSAGE_FORMAT = "%s %s";

    Validator validator;

    public void validate(Address address) {
        var dtoViolations = validator.validate(address, ValidationSequence.class);

        // @formatter:off
        var violations = Stream.of(dtoViolations.stream())
                .reduce(Stream::concat)
                .orElse(Stream.empty())
                .collect(Collectors.toSet());
        // @formatter:on

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
