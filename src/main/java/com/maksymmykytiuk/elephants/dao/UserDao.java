package com.maksymmykytiuk.elephants.dao;

import com.maksymmykytiuk.elephants.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, PagingAndSortingRepository<User, Long> {

    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
