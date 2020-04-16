package com.drbotro.bk.common.converter;

public interface Converter<T, K>{
    K convert(T object);
}