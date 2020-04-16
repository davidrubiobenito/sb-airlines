package com.drbotro.ss.common.converter;

public interface Converter<T, K>{
    K convert(T object);
}