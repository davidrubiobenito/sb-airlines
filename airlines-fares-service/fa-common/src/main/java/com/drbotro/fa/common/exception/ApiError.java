package com.drbotro.fa.common.exception;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.fa.common.model.AbstractModelBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError extends AbstractModelBean{

    private Integer code;
    private String description;
    private List<String> details;

    public ApiError(ApiErrorBuilder builder){
        this.code = builder.code;
        this.description = builder.description;
        this.details = builder.details;
    }

    public Integer getCode(){
        return code;
    }

    public String getDescription(){
        return description;
    }

    public List<String> getDetails(){
        return details;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof ApiError)){
            return false;
        }
        final ApiError castOther = (ApiError) other;
        return new EqualsBuilder().append(code, castOther.code).append(description, castOther.description)
                .append(details, castOther.details).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(code).append(description).append(details).toHashCode();
    }

    public static ApiErrorBuilder builder(){
        return new ApiErrorBuilder();
    }

    public ApiErrorBuilder cloneBuilder(){
        return new ApiErrorBuilder().withCode(this.code).withDescription(this.description).withDetails(this.details);
    }

    public interface IApiError{
        ApiError build();
    }

    public static class ApiErrorBuilder implements IApiError{

        private Integer code;
        private String description;
        private List<String> details;

        public ApiErrorBuilder withCode(Integer code){
            this.code = code;
            return self();
        }

        public ApiErrorBuilder withDescription(String description){
            this.description = description;
            return self();
        }

        public ApiErrorBuilder withDetails(List<String> details){
            this.details = details;
            return self();
        }

        @Override
        public ApiError build(){
            return new ApiError(this);
        }

        public ApiErrorBuilder self(){
            return this;
        }
    }

}
