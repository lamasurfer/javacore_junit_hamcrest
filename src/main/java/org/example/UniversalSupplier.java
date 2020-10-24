package org.example;

@FunctionalInterface
public interface UniversalSupplier<T> {
     T supply(String[] constructor);
}
