package com.cos.blog.repository;

import com.cos.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// DAO
// 자동으로 bean 등록이 된다.
// @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {

    // JPA Naming 쿼리 전략
    // SELECT * FROM User WHERE email = ?1 AND password = ?2;
//    User findByEmailAndPassword(String email, String password);

    // Native 쿼리 전략
//    @Query(value = "SELECT * FROM User WHERE email = ?1 AND password = ?2", nativeQuery = true)
//    User signIn(String email, String password);
}
