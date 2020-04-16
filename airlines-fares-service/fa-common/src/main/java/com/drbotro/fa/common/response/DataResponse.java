package com.drbotro.fa.common.response;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.fa.common.model.AbstractModelBean;

public class DataResponse<T>extends AbstractModelBean{

    private static final long serialVersionUID = -3716879007752905640L;

    private List<T> data;

    public DataResponse(DataResponseBuilder builder){
        this.data = builder.data;
    }

    public List<T> getData(){
        return data;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof DataResponse)){
            return false;
        }
        final DataResponse castOther = (DataResponse) other;
        return new EqualsBuilder().append(data, castOther.data).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(data).toHashCode();
    }

    public interface IDataResponse{
        DataResponse build();
    }

    public static class DataResponseBuilder<T> implements IDataResponse{

        private List<T> data;

        public DataResponseBuilder withData(List<T> data){
            this.data = data;
            return self();
        }

        @Override
        public DataResponse build(){
            return new DataResponse(this);
        }

        private DataResponseBuilder self(){
            return this;
        }

    }

}
