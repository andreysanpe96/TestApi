package com.service.wolox.api.model.error;

import lombok.Data;

@Data
public class ErrorMessage {

    private String detailError;

    private String source;

    private String date;
}
