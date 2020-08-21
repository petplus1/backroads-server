package com.backroads.service;

import com.backroads.entity.Authority;

public interface AuthorityServiceInterface  {
    Authority findByName(String name);
}
