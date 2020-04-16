package com.drbotro.ck.common.converter;

public interface Converter<T, K>{
    K convert(final T object);
}