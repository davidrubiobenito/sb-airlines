package com.drbotro.ck.common.model.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import com.drbotro.ck.common.model.AbstractModelBean;

public abstract class AbstractModelBeanTest<T extends AbstractModelBean>{

    protected T entityA1;
    protected T entityA2;
    protected T entityB;

    @Before
    public abstract void initEntities();

    @Test
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
