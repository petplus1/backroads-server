package com.backroads.service;


import com.backroads.entity.Authority;
import com.backroads.repository.AuthorityReposittory;
import com.backroads.service.AuthorityServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthorityService implements AuthorityServiceInterface {

    @Autowired
    AuthorityReposittory authorityReposittory;
    @Override
    public Authority findByName(String name) {
        return authorityReposittory.findByName(name);
    }
}
