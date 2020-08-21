package com.backroads.common;


import com.backroads.dto.UserDTO;
import com.backroads.entity.Authority;
import com.backroads.entity.User;
import com.backroads.service.AuthorityServiceInterface;
import com.backroads.service.UserServiceIterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@Component
public class UserDTOToUser implements Converter<UserDTO, User> {

    @Autowired
    UserServiceIterface userServiceIterface;
    @Autowired
    AuthorityServiceInterface authorityServiceInterface;
    @Override
    public User convert(UserDTO source) {
        Authority authority=authorityServiceInterface.findByName("ROLE_USER");
        User user;
        if(source.getId()==null){
            user=new User();
        }else{
            user=userServiceIterface.findOne(source.getId());
        }
        user.setId(source.getId());
        user.setName(source.getName());
        user.setUsername(source.getUsername());
        user.setPassword(source.getPassword());
        user.setEmail(source.getEmail());
        try {
            user.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(source.getCreationDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(user.getUserAuthority().size() >0) {
            user.getUserAuthority().clear();
            user.getUserAuthority().add(authority);
        }else{
            user.getUserAuthority().add(authority);

        }
        return user;
    }
}
