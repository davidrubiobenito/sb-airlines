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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.drbotro.fa.common.model.AbstractModelBean;
import com.google.common.collect.Sets;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "user_fare")
@AllArgsConstructor
@NoArgsConstructor
public class UserFare extends AbstractModelBean implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userFareId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_role_join_fare", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleFare> roles;

    @Column(name = "is_account_non_expired")
    private boolean isAccountNonExpired;
    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked;
    @Column(name = "is_credentials_non_expired")
    private boolean isCredentialsNonExpired;
    @Column(name = "is_enabled")
    private boolean isEnabled;

    private UserFare(UserApplicationBuilder builder){
        this.userFareId = builder.userFareId;
        this.username = builder.username;
        this.password = builder.password;
        this.roles = builder.roles;
        this.isAccountNonExpired = builder.isAccountNonExpired;
        this.isAccountNonLocked = builder.isAccountNonLocked;
        this.isCredentialsNonExpired = builder.isCredentialsNonExpired;
        this.isEnabled = builder.isEnabled;
    }

    public Long getUserFareId(){
        return userFareId;
    }

    @Override
    public Set<SimpleGrantedAuthority> getAuthorities(){
        Set<SimpleGrantedAuthority> grantedAuthoritiesRole = Sets.newHashSet();
        for(RoleFare role : getRoles()){
            grantedAuthoritiesRole.addAll(role.getGrantedAuthorities());
        }
        return grantedAuthoritiesRole;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public boolean isAccountNonExpired(){
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked(){
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled(){
        return isEnabled;
    }

    public Set<RoleFare> getRoles(){
        return roles;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof UserFare)){
            return false;
        }
        final UserFare castOther = (UserFare) other;
        return new EqualsBuilder().append(userFareId, castOther.userFareId).append(username, castOther.username)
                .append(password, castOther.password).append(roles, castOther.roles)
                .append(isAccountNonExpired, castOther.isAccountNonExpired)
                .append(isAccountNonLocked, castOther.isAccountNonLocked)
                .append(isCredentialsNonExpired, castOther.isCredentialsNonExpired)
                .append(isEnabled, castOther.isEnabled).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(userFareId).append(username).append(password).append(roles)
                .append(isAccountNonExpired).append(isAccountNonLocked).append(isCredentialsNonExpired)
                .append(isEnabled).toHashCode();
    }

    public static UserApplicationBuilder builder(){
        return new UserApplicationBuilder();
    }

    public UserApplicationBuilder cloneBuilder(){
        return new UserApplicationBuilder().withUserFareId(this.userFareId).withUsername(this.username)
                .withPassword(this.password).withRoles(this.roles).withIsAccountNonExpired(this.isAccountNonExpired)
                .withIsAccountNonLocked(this.isAccountNonLocked)
                .withIsCredentialsNonExpired(this.isCredentialsNonExpired).withIsEnabled(this.isEnabled);
    }

    public interface IUserApplicationBuilder{
        UserFare build();
    }

    public static class UserApplicationBuilder implements IUserApplicationBuilder{

        private Long userFareId;
        private String username;
        private String password;
        private Set<RoleFare> roles;
        private boolean isAccountNonExpired;
        private boolean isAccountNonLocked;
        private boolean isCredentialsNonExpired;
        private boolean isEnabled;

        public UserApplicationBuilder withUserFareId(Long userApplicationId){
            this.userFareId = userApplicationId;
            return self();
        }

        public UserApplicationBuilder withUsername(String username){
            this.username = username;
            return self();
        }

        public UserApplicationBuilder withPassword(String password){
            this.password = password;
            return self();
        }

        public UserApplicationBuilder withRoles(Set<RoleFare> roles){
            this.roles = roles;
            return self();
        }

        public UserApplicationBuilder withIsAccountNonExpired(boolean isAccountNonExpired){
            this.isAccountNonExpired = isAccountNonExpired;
            return self();
        }

        public UserApplicationBuilder withIsAccountNonLocked(boolean isAccountNonLocked){
            this.isAccountNonLocked = isAccountNonLocked;
            return self();
        }

        public UserApplicationBuilder withIsCredentialsNonExpired(boolean isCredentialsNonExpired){
            this.isCredentialsNonExpired = isCredentialsNonExpired;
            return self();
        }

        public UserApplicationBuilder withIsEnabled(boolean isEnabled){
            this.isEnabled = isEnabled;
            return self();
        }

        @Override
        public UserFare build(){
            return new UserFare(this);
        }

        private UserApplicationBuilder self(){
            return this;
        }

    }

}
