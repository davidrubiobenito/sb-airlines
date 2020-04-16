package com.drbotro.fa.common.converter;

public interface Converter<T, K>{
    K convert(final T object);
}