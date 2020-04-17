package com.drbotro.bk.webapi.response.v2;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.bk.common.response.GenericResponse;

@SuppressWarnings("serial")
public class GenericResponsePassengerWebResponse extends GenericResponse{

    private List<PassengerWebResponse> data;

    private GenericResponsePassengerWebResponse(){
    }

    public List<PassengerWebResponse> getData(){
        return data;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof GenericResponsePassengerWebResponse)){
            return false;
        }
        final GenericResponsePassengerWebResponse castOther = (GenericResponsePassengerWebResponse) other;
        return new EqualsBuilder().appendSuper(super.equals(other)).append(data, castOther.data).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().appendSuper(super.hashCode()).append(data).toHashCode();
    }

    public static GenericResponsePassengerWebResponseBuilder builder(){
        return new GenericResponsePassengerWebResponseBuilder();
    }

    @Override
    public GenericResponsePassengerWebResponseBuilder cloneBuilder(){
        return new GenericResponsePassengerWebResponseBuilder().withStatus(this.status).withError(this.error)
                .withData(this.data);
    }

    public interface IGenericResponsePassengerWebResponse{
        GenericResponsePassengerWebResponse build();
    }

    public static class GenericResponsePassengerWebResponseBuilder extends
            GenericResponseBuilder<GenericResponsePassengerWebResponse, GenericResponsePassengerWebResponseBuilder>
            implements IGenericResponsePassengerWebResponse{

        @Override
        protected GenericResponsePassengerWebResponse getObject(){
            return new GenericResponsePassengerWebResponse();
        }

        @Override
        protected GenericResponsePassengerWebResponseBuilder thisObject(){
            return this;
        }

        public GenericResponsePassengerWebResponseBuilder(){
        }

        public GenericResponsePassengerWebResponseBuilder withData(List<PassengerWebResponse> data){
            object.data = data;
            return this;
        }
    }

}
