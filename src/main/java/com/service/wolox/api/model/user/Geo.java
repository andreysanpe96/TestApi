package com.service.wolox.api.model.user;

import lombok.*;

import java.io.Serializable;

@Data
public class Geo implements Serializable {

    private Double lat;

    private Double lng;
}
