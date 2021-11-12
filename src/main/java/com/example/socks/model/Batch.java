package com.example.socks.model;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Value
@Builder
public class Batch {
    String color;
    @Max(value = 100, message = "Expected to be more 0 and less 100")
    @Min(value = 0, message = "Expected to be more 0 and less 100")
    int cottonPart;
    @Positive
    int quantity;

}
