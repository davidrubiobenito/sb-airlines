package com.drbotro.fa.repository.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.drbotro.fa.common.model.AbstractModelBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "role_fare")
@AllArgsConstructor
@NoArgsConstructor
public class RoleFare extends AbstractModelBean{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleFareId;
    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<UserFare> users;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "role_permission_join_fare", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<PermissionFare> permissions;

    private RoleFare(RoleFareBuilder builder){
        this.roleFareId = builder.roleFareId;
        this.roleName = builder.roleName;
        this.users = builder.users;
        this.permissions = builder.permissions;
    }

    public Long getRoleFareId(){
        return roleFareId;
    }

    public String getRoleName(){
        return roleName;
    }

    public Set<UserFare> getUsers(){
        return users;
    }

    public Set<PermissionFare> getPermisions(){
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        /*
        Set<SimpleGrantedAuthority> grantedAuthoritiesRole = getPermisions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName()))
                .collect(Collectors.toSet());
                */

        Set<SimpleGrantedAuthority> simpleGrantedAuthorityPermission = Sets.newHashSet();

        for(PermissionFare permission : getPermisions()){
            if(!Strings.isEmpty(permission.getPermissionName())){
                simpleGrantedAuthorityPermission.add(new SimpleGrantedAuthority(permission.getPermissionName()));
            }
        }

        simpleGrantedAuthorityPermission.add(new SimpleGrantedAuthority("ROLE_" + this.roleName));
        return simpleGrantedAuthorityPermission;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof RoleFare)){
            return false;
        }
        final RoleFare castOther = (RoleFare) other;
        return new EqualsBuilder().append(roleFareId, castOther.roleFareId).append(roleName, castOther.roleName)
                .append(users, castOther.users).append(permissions, castOther.permissions).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(roleFareId).append(roleName).append(users).append(permissions).toHashCode();
    }

    public static RoleFareBuilder builder(){
        return new RoleFareBuilder();
    }

    public RoleFareBuilder cloneBuilder(){
        return new RoleFareBuilder().withRoleId(this.roleFareId).withRoleName(this.roleName).withUsers(this.users)
                .withPermission(this.permissions);
    }

    public interface IRoleFareBuilder{
        RoleFare build();
    }

    public static class RoleFareBuilder implements IRoleFareBuilder{

        private Long roleFareId;
        private String roleName;
        private Set<UserFare> users;
        private Set<PermissionFare> permissions;

        public RoleFareBuilder withRoleId(Long permissionFareId){
            this.roleFareId = permissionFareId;
            return self();
        }

        public RoleFareBuilder withRoleName(String roleName){
            this.roleName = roleName;
            return self();
        }

        public RoleFareBuilder withUsers(Set<UserFare> users){
            this.users = users;
            return self();
        }

        public RoleFareBuilder withPermission(Set<PermissionFare> permissions){
            this.permissions = permissions;
            return self();
        }

        @Override
        public RoleFare build(){
            return new RoleFare(this);
        }

        private RoleFareBuilder self(){
            return this;
        }

    }

}
