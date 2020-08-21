package com.backroads.service;


import com.backroads.entity.Location;
import com.backroads.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface UserServiceIterface {

    User findOne(Integer userId);

    User findByUsername(String username);

    List<User> findAll();

    User save(User users);

    void remove(Integer id);

    Page<User> searchUsers(
            @Param("name") String name,
            @Param("username") String username,
            @Param("model") boolean enable,
            int pageNum
    );
    Page<User> findAll(int pageNum);

    Page<User> searchByName(@Param("name") String name, int pageNum, int pageSize);
}
