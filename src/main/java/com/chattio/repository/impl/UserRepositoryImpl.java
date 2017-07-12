package com.chattio.repository.impl;

import com.chattio.constants.UserConstants;
import com.chattio.entity.User;
import com.chattio.repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByEmail(String email) {
        return entityManager.createQuery("SELECT us from User as us where us.email = :email", User.class)
                .setParameter(UserConstants.Entity.EMAIL, email)
                .getSingleResult();
    }

    @Override
    public List<User> findAll(String exceptUsername) {
        return entityManager.createQuery("SELECT u FROM User as u WHERE u.email <> :email ORDER BY email", User.class)
                .setParameter(UserConstants.Entity.EMAIL, exceptUsername)
                .getResultList();
    }
}
