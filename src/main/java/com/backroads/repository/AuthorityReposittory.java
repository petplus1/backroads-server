package com.backroads.repository;


import com.backroads.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorityReposittory extends JpaRepository<Authority,Integer> {

    Authority findByName(String name);
}
