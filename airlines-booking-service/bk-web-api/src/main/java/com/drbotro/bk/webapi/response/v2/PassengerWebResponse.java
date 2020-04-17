package com.drbotro.bk.webapi.response.v2;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.bk.common.model.AbstractModelBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerWebResponse extends AbstractModelBean{

    private long id;
    private String firstName;
    private String lastName;
    private String gender;

    private PassengerWebResponse(PassengerWebResponseBuilder builder){
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.gender = builder.gender;
    }

    public Long getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getGender(){
        return gender;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof PassengerWebResponse)){
            return false;
        }
        final PassengerWebResponse castOther = (PassengerWebResponse) other;
        return new EqualsBuilder().append(id, castOther.id).append(firstName, castOther.firstName)
                .append(lastName, castOther.lastName).append(gender, castOther.gender).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(id).append(firstName).append(lastName).append(gender).toHashCode();
    }

    public static PassengerWebResponseBuilder builder(){
        return new PassengerWebResponseBuilder();
    }

    public PassengerWebResponseBuilder cloneBuilder(){
        return new PassengerWebResponseBuilder().withId(this.id).withFirstname(this.firstName)
                .withLastName(this.lastName).withGender(this.gender);
    }

    public interface IPassengerWebResponseBuilder{
        PassengerWebResponse build();
    }

    public static final class PassengerWebResponseBuilder implements IPassengerWebResponseBuilder{

        private long id;
        private String firstName;
        private String lastName;
        private String gender;

        public PassengerWebResponseBuilder withId(long id){
            this.id = id;
            return self();
        }

        public PassengerWebResponseBuilder withFirstname(String firstName){
            this.firstName = firstName;
            return self();
        }

        public PassengerWebResponseBuilder withLastName(String lastName){
            this.lastName = lastName;
            return self();
        }

        public PassengerWebResponseBuilder withGender(String gender){
            this.gender = gender;
            return self();
        }

        @Override
        public PassengerWebResponse build(){
            return new PassengerWebResponse(this);
        }

        private PassengerWebResponseBuilder self(){
            return this;
        }
    }

}
