package com.drbotro.bk.common.response;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.bk.common.exception.ErrorException;
import com.drbotro.bk.common.model.AbstractModelBean;

public abstract class GenericResponse extends AbstractModelBean{

    protected String status;
    protected ErrorException error;

    protected GenericResponse(){
    }

    public String getStatus(){
        return status;
    }

    public ErrorException getError(){
        return error;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof GenericResponse)){
            return false;
        }
        final GenericResponse castOther = (GenericResponse) other;
        return new EqualsBuilder().append(status, castOther.status).append(error, castOther.error).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(status).append(error).toHashCode();
    }

    public abstract <T extends GenericResponseBuilder> T cloneBuilder();

    public interface IGenericResponseBuilder{
        GenericResponse build();
    }

    protected abstract static class GenericResponseBuilder<T extends GenericResponse, B extends GenericResponseBuilder<T, B>>
            implements IGenericResponseBuilder{

        protected T object;
        protected B thisObject;

        protected abstract T getObject(); //Each concrete implementing subclass overrides this so that T becomes an object of the concrete subclass

        protected abstract B thisObject(); //Each concrete implementing subclass builder overrides this for the same reason, but for B for the builder

        protected GenericResponseBuilder(){
            object = getObject();
            thisObject = thisObject();
        }

        public B withStatus(String status){
            object.status = status;
            return thisObject();
        }

        public B withError(ErrorException error){
            object.error = error;
            return thisObject();
        }

        @Override
        public T build(){
            return object;
        }
    }

}
