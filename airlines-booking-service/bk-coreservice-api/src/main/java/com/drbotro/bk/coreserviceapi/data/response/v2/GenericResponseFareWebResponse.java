package com.drbotro.bk.coreserviceapi.data.response.v2;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.bk.common.response.GenericResponse;

public class GenericResponseFareWebResponse extends GenericResponse{

    private List<FareWebResponse> data;

    private GenericResponseFareWebResponse(){
    }

    public List<FareWebResponse> getData(){
        return data;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof GenericResponseFareWebResponse)){
            return false;
        }
        final GenericResponseFareWebResponse castOther = (GenericResponseFareWebResponse) other;
        return new EqualsBuilder().appendSuper(super.equals(other)).append(data, castOther.data).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().appendSuper(super.hashCode()).append(data).toHashCode();
    }

    public static GenericResponseFareWebResponseBuilder builder(){
        return new GenericResponseFareWebResponseBuilder();
    }

    @Override
    public GenericResponseFareWebResponseBuilder cloneBuilder(){
        return new GenericResponseFareWebResponseBuilder().withStatus(this.status).withError(this.error)
                .withData(this.data);
    }

    public interface IGenericResponseFareWebResponse{
        GenericResponseFareWebResponse build();
    }

    public static class GenericResponseFareWebResponseBuilder
            extends GenericResponseBuilder<GenericResponseFareWebResponse, GenericResponseFareWebResponseBuilder>
            implements IGenericResponseFareWebResponse{

        @Override
        protected GenericResponseFareWebResponse getObject(){
            return new GenericResponseFareWebResponse();
        }

        @Override
        protected GenericResponseFareWebResponseBuilder thisObject(){
            return this;
        }

        public GenericResponseFareWebResponseBuilder(){
        }

        public GenericResponseFareWebResponseBuilder withData(List<FareWebResponse> data){
            object.data = data;
            return this;
        }
    }

}
