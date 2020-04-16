package com.drbotro.ss.common.converter.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.drbotro.ss.common.converter.Converter;

public abstract class ConverterTest<T, K>{

    private Converter<T, K> converter;

    private T completeInitialValue;

    private T minimalInitialValue;

    private K completeExpectedValue;

    private K minimalExpectedValue;

    public abstract Converter<T, K> getConverter();

    public abstract T getCompleteInitialValue();

    public abstract T getMinimalInitialValue();

    public abstract K getCompleteExpectedValue();

    public abstract K getMinimalExpectedValue();

    @Before
    public void init(){
        this.converter = getConverter();
        this.completeInitialValue = getCompleteInitialValue();

        this.minimalInitialValue = getMinimalInitialValue();
        this.minimalExpectedValue = getMinimalExpectedValue();
    }

    @Test
    public void shouldConvertNull(){
        assertThat(converter.convert(null), is(nullValue()));
    }

    @Test
    public void shouldConvertCompleteInputIntoExpectedValue(){
        assertThat(converter.convert(completeInitialValue), is(equalTo(completeExpectedValue)));
    }

    @Test
    public void shouldConvertMinimumInputIntoExpectedValue(){
        assertThat(converter.convert(minimalInitialValue), is(equalTo(minimalExpectedValue)));
    }

}
