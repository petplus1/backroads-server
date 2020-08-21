package com.backroads.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable,UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",unique = true,nullable = false)
    private Integer id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "username",nullable = false,unique = true)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name="enable")
    private boolean enable;
    @OneToMany(mappedBy = "user")
    private Set<Reservation>reservations=new HashSet<>();
    @Column(name="createDate")
    private Date createdDate;
    @Column(name="email")
    private String email;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "authority_id"))
    private Set<Authority> userAuthority=new HashSet<Authority>();
    public User() {
    }

    public User(String name, String username, String password,Date createdDate,String email, Set<Authority> userAuthority) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.userAuthority = userAuthority;
        this.createdDate=createdDate;
        this.email=email;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Set<Authority> getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(Set<Authority> userAuthority) {
        this.userAuthority = userAuthority;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userAuthority;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    public Set<Reservation> getReservations() {
        return reservations;
    }
    @JsonIgnore
    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
