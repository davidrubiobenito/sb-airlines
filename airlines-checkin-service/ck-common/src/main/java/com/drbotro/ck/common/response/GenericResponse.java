package com.drbotro.ck.common.response;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.ck.common.exception.ErrorException;
import com.drbotro.ck.common.model.AbstractModelBean;

@SuppressWarnings("serial")
public class GenericResponse<T>extends AbstractModelBean{

    private String status;
    private List<T> data;
    private ErrorException error;

    private GenericResponse(final GenericResponseBuilder builder){
        this.status = builder.status;
        this.data = builder.data;
        this.error = builder.error;
    }

    public String getStatus(){
        return status;
    }

    public List<T> getData(){
        return data;
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
        return new EqualsBuilder().append(status, castOther.status).append(data, castOther.data)
                .append(error, castOther.error).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(status).append(data).append(error).toHashCode();
    }

    public static GenericResponseBuilder builder(){
        return new GenericResponseBuilder();
    }

    public GenericResponseBuilder cloneBuilder(){
        return new GenericResponseBuilder().withStatus(this.status).withData(this.data).withError(this.error);
    }

    public interface IGenericResponseBuilder{
        GenericResponse build();
    }

    public static class GenericResponseBuilder<T> implements IGenericResponseBuilder{

        private String status;
        private List<T> data;
        private ErrorException error;

        public GenericResponseBuilder withStatus(String status){
            this.status = status;
            return self();
        }

        public GenericResponseBuilder withData(List<T> data){
            this.data = data;
            return self();
        }

        public GenericResponseBuilder withError(ErrorException error){
            this.error = error;
            return self();
        }

        @Override
        public GenericResponse build(){
            return new GenericResponse(this);
        }

        private GenericResponseBuilder self(){
            return this;
        }
    }

}
