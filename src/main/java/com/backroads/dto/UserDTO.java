package com.backroads.dto;


import com.backroads.entity.Authority;
import com.backroads.entity.User;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

public class UserDTO implements Serializable {

    private Integer id;
    private String name;
    private String username;
    private String password;
    private String creationDate;
    private String email;
    private Set<Authority> userAuthority=new HashSet<>();
    public UserDTO() {
    }

    public UserDTO(Integer id, String name, String username,String email, String password,String creationDate) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.creationDate=creationDate;
        this.email=email;
    }

    public UserDTO(User user) {
        this(user.getId(),user.getName(),user.getUsername(),user.getPassword(),user.getEmail(),new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(user.getCreatedDate()));
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Authority> getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(Set<Authority> userAuthority) {
        this.userAuthority = userAuthority;
    }
}
