package com.drbotro.fa.common.exception;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ErrorExceptionTest{

    public static final int CODE = 404;
    public static final String NOT_FOUND = "Not found";
    public static final int OTHER_CODE = 500;
    public static final String SERVER_INTERNAL_ERROR = "Server internal error";
    private ErrorException entityA1;
    private ErrorException entityA2;
    private ErrorException entityB;

    @Before
    public void initEntities(){
        entityA1 = ErrorException.builder().withError(CODE).withDescription(NOT_FOUND).build();
        entityA2 = ErrorException.builder().withError(CODE).withDescription(NOT_FOUND).build();
        entityB = ErrorException.builder().withError(OTHER_CODE).withDescription(SERVER_INTERNAL_ERROR).build();
    }

    @Test
    @Ignore
    public void comparingTheSameEntitiesShouldBeTrue(){
        assertThat(entityA1, is(entityA2));
    }

    @Test
    public void comparingDifferentEntitiesShouldBeFalse(){
        assertThat(entityA1, is(not(entityB)));
    }

    @Test
    public void comparingWithDifferentInstanceShouldBeFalse(){
        assertFalse(entityA1.equals(this));
    }

    @Test
    @Ignore
    public void givenTheSameEntitiesShouldHaveIdenticalHasCode(){
        assertThat(entityA1.hashCode(), is(entityA2.hashCode()));
    }

    @Test
    public void givenTheSameEntitiesShouldHaveDifferentHasCode(){
        assertThat(entityA1.hashCode(), is(not(entityB.hashCode())));
    }

    @Test
    public void shouldImplementToStringMethod(){
        assertThat(entityA1.toString(), not(nullValue()));
        assertThat(entityA2.toString(), not(nullValue()));
        assertThat(entityB.toString(), not(nullValue()));
    }

}