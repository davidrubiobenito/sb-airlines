package com.drbotro.bk.webapi.response;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.bk.common.response.GenericResponse;

public class GenericResponseBookingRecordWebResponse extends GenericResponse{

    private List<BookingRecordWebResponse> data;

    private GenericResponseBookingRecordWebResponse(){
    }

    public List<BookingRecordWebResponse> getData(){
        return data;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof GenericResponseBookingRecordWebResponse)){
            return false;
        }
        final GenericResponseBookingRecordWebResponse castOther = (GenericResponseBookingRecordWebResponse) other;
        return new EqualsBuilder().appendSuper(super.equals(other)).append(data, castOther.data).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().appendSuper(super.hashCode()).append(data).toHashCode();
    }

    public static GenericResponseBookingRecordWebResponseBuilder builder(){
        return new GenericResponseBookingRecordWebResponseBuilder();
    }

    @Override
    public GenericResponseBookingRecordWebResponseBuilder cloneBuilder(){
        return new GenericResponseBookingRecordWebResponseBuilder().withStatus(this.status).withError(this.error)
                .withData(this.data);
    }

    public interface IGenericResponseBookingRecordWebResponse{
        GenericResponseBookingRecordWebResponse build();
    }

    public static class GenericResponseBookingRecordWebResponseBuilder extends
            GenericResponseBuilder<GenericResponseBookingRecordWebResponse, GenericResponseBookingRecordWebResponseBuilder>
            implements IGenericResponseBookingRecordWebResponse{

        @Override
        protected GenericResponseBookingRecordWebResponse getObject(){
            return new GenericResponseBookingRecordWebResponse();
        }

        @Override
        protected GenericResponseBookingRecordWebResponseBuilder thisObject(){
            return this;
        }

        public GenericResponseBookingRecordWebResponseBuilder(){
        }

        public GenericResponseBookingRecordWebResponseBuilder withData(List<BookingRecordWebResponse> data){
            object.data = data;
            return this;
        }
    }

}
