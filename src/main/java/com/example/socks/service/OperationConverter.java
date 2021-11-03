package com.example.socks.service;

import com.example.socks.controller.Operation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OperationConverter implements Converter <String, Operation> {

    @Override
    public Operation convert(String source) {
        return Operation.find(source);
    }
}
