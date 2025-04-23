package com.nagis.company.ecommerce.model.user;

import jakarta.persistence.Embeddable;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {

    @Pattern(regexp = "^[0-9]{5}-[0-9]{3}$", message = "CEP inválido")
    private String cep;

    private String state;

    private String city;

    private String district;

    private String street;

    private String number;
}
