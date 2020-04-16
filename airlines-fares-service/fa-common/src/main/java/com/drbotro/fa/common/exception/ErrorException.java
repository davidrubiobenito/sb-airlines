package com.drbotro.fa.common.exception;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.drbotro.fa.common.response.DataResponse;

public class ErrorException extends Exception{

    private static final long serialVersionUID = 5767551160120356470L;

    private Integer code;

    public ErrorException(ErrorResponseBuilder builder){
        super(builder.description);
        this.code = builder.code;
    }

    public Integer getCode(){
        return code;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof DataResponse)){
            return false;
        }
        final ErrorException castOther = (ErrorException) other;
        return new EqualsBuilder().appendSuper(super.equals(other)).append(code, castOther.code).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().appendSuper(super.hashCode()).append(code).toHashCode();
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }

    public static ErrorResponseBuilder builder(){
        return new ErrorResponseBuilder();
    }

    public ErrorResponseBuilder cloneBuilder(){
        return new ErrorResponseBuilder().withError(this.code).withDescription(this.getMessage());
    }

    public interface IErrorResponse{
        ErrorException build();
    }

    public static class ErrorResponseBuilder implements IErrorResponse{

        private Integer code;
        private String description;

        public ErrorResponseBuilder withError(Integer code){
            this.code = code;
            return self();
        }

        public ErrorResponseBuilder withDescription(String description){
            this.description = description;
            return self();
        }

        @Override
        public ErrorException build(){
            return new ErrorException(this);
        }

        public ErrorResponseBuilder self(){
            return this;
        }
    }

}
