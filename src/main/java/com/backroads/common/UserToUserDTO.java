package com.backroads.common;


import com.backroads.dto.UserDTO;
import com.backroads.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserToUserDTO implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(User source) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(source.getId());
        userDTO.setName(source.getName());
        userDTO.setUsername(source.getUsername());
        userDTO.setPassword(source.getPassword());
        userDTO.setEmail(source.getEmail());
        userDTO.setCreationDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(source.getCreatedDate()));
        userDTO.setUserAuthority(source.getUserAuthority());
        return userDTO;
    }
    public List<UserDTO> convert(List<User> users){
        List<UserDTO> dtos= new ArrayList<>();
        for(User u : users){
            dtos.add(convert(u));
        }
        return dtos;
    }
}
