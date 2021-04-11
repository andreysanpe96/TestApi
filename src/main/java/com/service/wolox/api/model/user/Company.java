package com.service.wolox.api.model.user;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
@Builder
public class Company implements Serializable {

    private String name;

    private String catchPhrase;

    private String bs;


}
