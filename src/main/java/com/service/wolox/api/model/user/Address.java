package com.service.wolox.api.model.user;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
@Builder
public class Address implements Serializable {

    private String street;

    private String suite;

    private String city;

    private String zipcode;

    private Geo geo;

}
