package com.example.socks.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ErrorResponse {
    String message;
}
