package com.example.socks;

@FunctionalInterface
public interface MyInterface {

    String hello();

    public static void main(String[] args) {
        doStaff(() -> "hello");
    }

    private static void doStaff(MyInterface myInterface) {

    }
}
