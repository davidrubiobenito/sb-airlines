package com.drbotro.fa.repository.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.fa.common.model.AbstractModelBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "permission_fare")
@AllArgsConstructor
@NoArgsConstructor
public class PermissionFare extends AbstractModelBean{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long permissionFareId;
    @Column(name = "permission_name")
    private String permissionName;

    @ManyToMany(mappedBy = "permissions")
    @JsonIgnore
    private Set<RoleFare> roles;

    private PermissionFare(PermissionFareBuilder builder){
        this.permissionFareId = builder.permissionFareId;
        this.permissionName = builder.permissionName;
        this.roles = builder.roles;
    }

    public Long getPermissionFareId(){
        return permissionFareId;
    }

    public String getPermissionName(){
        return permissionName;
    }

    public Set<RoleFare> getRoles(){
        return roles;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof PermissionFare)){
            return false;
        }
        final PermissionFare castOther = (PermissionFare) other;
        return new EqualsBuilder().append(permissionFareId, castOther.permissionFareId)
                .append(permissionName, castOther.permissionName).append(roles, castOther.roles).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(permissionFareId).append(permissionName).append(roles).toHashCode();
    }

    public static PermissionFareBuilder builder(){
        return new PermissionFareBuilder();
    }

    public PermissionFareBuilder cloneBuilder(){
        return new PermissionFareBuilder().withPermissionFareIdId(this.permissionFareId)
                .withPermissionName(this.permissionName).withRoles(this.roles);
    }

    public interface IPermissionFareBuilder{
        PermissionFare build();
    }

    public static class PermissionFareBuilder implements IPermissionFareBuilder{

        private Long permissionFareId;
        private String permissionName;
        private Set<RoleFare> roles;

        public PermissionFareBuilder withPermissionFareIdId(Long permissionFareId){
            this.permissionFareId = permissionFareId;
            return self();
        }

        public PermissionFareBuilder withPermissionName(String permissionName){
            this.permissionName = permissionName;
            return self();
        }

        public PermissionFareBuilder withRoles(Set<RoleFare> roles){
            this.roles = roles;
            return self();
        }

        @Override
        public PermissionFare build(){
            return new PermissionFare(this);
        }

        private PermissionFareBuilder self(){
            return this;
        }

    }

}
