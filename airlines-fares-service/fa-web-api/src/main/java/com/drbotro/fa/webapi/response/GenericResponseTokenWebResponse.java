package com.drbotro.fa.webapi.response;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.fa.common.response.GenericResponse;

@SuppressWarnings("serial")
public class GenericResponseTokenWebResponse extends GenericResponse{

    private List<String> data;

    private GenericResponseTokenWebResponse(){
    }

    public List<String> getData(){
        return data;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof GenericResponseTokenWebResponse)){
            return false;
        }
        final GenericResponseTokenWebResponse castOther = (GenericResponseTokenWebResponse) other;
        return new EqualsBuilder().appendSuper(super.equals(other)).append(data, castOther.data).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().appendSuper(super.hashCode()).append(data).toHashCode();
    }

    public static GenericResponseTokenWebResponseBuilder builder(){
        return new GenericResponseTokenWebResponseBuilder();
    }

    @Override
    public GenericResponseTokenWebResponseBuilder cloneBuilder(){
        return new GenericResponseTokenWebResponseBuilder().withStatus(this.status).withError(this.error)
                .withData(this.data);
    }

    public interface IGenericResponseTokenWebResponse{
        GenericResponseTokenWebResponse build();
    }

    public static class GenericResponseTokenWebResponseBuilder
            extends GenericResponseBuilder<GenericResponseTokenWebResponse, GenericResponseTokenWebResponseBuilder>
            implements IGenericResponseTokenWebResponse{

        @Override
        protected GenericResponseTokenWebResponse getObject(){
            return new GenericResponseTokenWebResponse();
        }

        @Override
        protected GenericResponseTokenWebResponseBuilder thisObject(){
            return this;
        }

        public GenericResponseTokenWebResponseBuilder(){
        }

        public GenericResponseTokenWebResponseBuilder withData(List<String> data){
            object.data = data;
            return this;
        }
    }

}
