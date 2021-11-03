package com.example.socks.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Sock {

    String color;
    int cottonPart;
    int quantity;


}
