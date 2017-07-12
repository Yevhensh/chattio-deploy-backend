package com.chattio.repository.impl;

import com.chattio.repository.BaseRepository;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public abstract class BaseRepositoryImpl<T> implements BaseRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> entityClass;

    public BaseRepositoryImpl() {
        entityClass = getEntityClass();
    }

    @SuppressWarnings("unchecked")
    protected final Class<T> getEntityClass() {
        Class<?> entityClass = GenericTypeResolver
                .resolveTypeArgument(getClass(), BaseRepositoryImpl.class);

        if (entityClass != null) {
            return (Class<T>) entityClass;
        }
        throw new IllegalArgumentException("Could not resolve entity class");
    }

    public T findById(Object id) {
        return entityManager.find(getEntityClass(), id);
    }

    @Transactional
    public void delete(T entity) {
        entityManager.remove(entityManager.merge(entity));
    }

    @Transactional
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Transactional
    public void create(T entity) {
        entityManager.persist(entity);
    }

    public List<T> findAll() {
        return entityManager
                .createQuery("FROM " + entityClass.getSimpleName()
                        + " ORDER BY email", getEntityClass())
                .getResultList();
    }

    @Transactional
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM "
                + entityClass.getSimpleName()).executeUpdate();
    }
}
