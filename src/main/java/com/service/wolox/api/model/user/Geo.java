package com.service.wolox.api.model.user;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
@Builder
public class Geo implements Serializable {

    private Double lat;

    private Double lng;
}
