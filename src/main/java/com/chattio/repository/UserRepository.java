package com.chattio.repository;

import com.chattio.entity.Dialog;
import com.chattio.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends BaseRepository<User> {
    User findByEmail(String email);

    List<User> findAll(String exceptUsername);

}
