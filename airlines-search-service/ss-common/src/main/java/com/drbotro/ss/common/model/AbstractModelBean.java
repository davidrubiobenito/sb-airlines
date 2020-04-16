package com.drbotro.ss.common.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public abstract class AbstractModelBean implements Serializable{

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
