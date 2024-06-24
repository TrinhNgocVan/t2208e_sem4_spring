package org.example.repository;

import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> , JpaSpecificationExecutor<User> {

    @Query(value = "from User where username = :username and address = :address")
    List<User> findByUsernameAndAddress(@Param(value = "username") String username
            ,@Param(value = "address") String address);
    // nativeQuery = false -> hql (hibernate query language + hibernate dialect )

}
