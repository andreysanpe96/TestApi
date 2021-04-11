package com.service.wolox.api.model.album;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
@Builder
public class Album implements Serializable {

    private Long userId;

    private Long id;

    private String title;
}
