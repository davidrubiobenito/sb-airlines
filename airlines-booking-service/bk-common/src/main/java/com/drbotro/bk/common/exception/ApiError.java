package com.drbotro.bk.common.exception;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ApiError extends Exception{

    private static final long serialVersionUID = 5767551160120356470L;

    private Integer code;

    public ApiError(ErrorResponseBuilder builder){
        super(builder.description);
        this.code = builder.code;
    }

    public Integer getCode(){
        return code;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof ApiError)){
            return false;
        }
        final ApiError castOther = (ApiError) other;
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
        ApiError build();
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
        public ApiError build(){
            return new ApiError(this);
        }

        public ErrorResponseBuilder self(){
            return this;
        }
    }

}
