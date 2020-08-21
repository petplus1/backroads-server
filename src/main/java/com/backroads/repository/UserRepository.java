package com.backroads.repository;


import com.backroads.entity.Location;
import com.backroads.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE "
            + "(:name IS NULL or u.name like :name ) AND "
            + "(:username IS NULL OR u.username like :username) AND"
            + "(:enable IS NULL OR u.enable = :enable)"
    )
    Page<User> searchUser(
            @Param("name") String name,
            @Param("username") String username,
            @Param("enable") boolean enable,
            Pageable pageable);
    @Query("select u from User  u where u.username like :name")
    Page<User> searchByName(@Param("name")String name, Pageable pageable);
}
