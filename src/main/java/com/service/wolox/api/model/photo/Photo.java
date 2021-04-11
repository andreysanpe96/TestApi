package com.service.wolox.api.model.photo;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
@Builder
public class Photo implements Serializable{

    private Long albumId;

    private Long id;

    private String title;

    private String url;

    private String thumbnailUrl;
}
