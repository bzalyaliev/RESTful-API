package com.example.socks.controller;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Operation {
    MORE_THAN("moreThan"), LESS_THAN("lessThan"), EQUAL("equal");
    private final String value;

    public static Operation find(String value) {
        for (Operation operation : Operation.values()) {
            if (operation.value.equals(value)) {
                return operation;
            }
        }
        throw new IllegalStateException("Not expected operation value");
    }
}
