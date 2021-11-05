package com.example.socks.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Batch {
    String color;
    int cottonPart;
    int quantity;

}
