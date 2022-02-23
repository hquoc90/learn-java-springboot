package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
        public User findByEmail(String email);
        public User findByPhone(String phone);

        @Query(value = "SELECT * FROM user u WHERE u.full_name like %?1% ", nativeQuery = true)
        public User findUserByName( String name);
        //tuy chinh cac ham thuc hien truy van tu db
        //Custom query
        @Query(value = "select * from user u where u.email = ?1", nativeQuery = true)
        public User myCustomQuery2(String email);

}
