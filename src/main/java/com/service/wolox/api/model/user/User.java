package com.service.wolox.api.model.user;

import lombok.*;

import java.io.Serializable;


@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
@Builder
public class User implements Serializable {

    private Long id;

    private String name;

    private String username;

    private String email;

    private Address address;

    private String phone;

    private String website;

    private Company company;

}
