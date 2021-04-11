package com.service.wolox.api.model.comment;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
@Builder
public class Comment implements Serializable {

    private Long postId;

    private Long id;

    private String name;

    private String email;

    private String body;
}
