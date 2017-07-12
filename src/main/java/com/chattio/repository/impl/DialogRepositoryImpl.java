package com.chattio.repository.impl;

import com.chattio.constants.DialogConstants;
import com.chattio.constants.UserConstants;
import com.chattio.entity.*;
import com.chattio.repository.DialogRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class DialogRepositoryImpl extends BaseRepositoryImpl<Dialog> implements DialogRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createDialog(User firstUser, User secondUser) {
        Dialog dialog = new Dialog();
        dialog.setDialogUsers(Arrays.asList(firstUser, secondUser));
        this.create(dialog);
    }

    @Override
    public List<Message> findMessagesByDialog(Long dialogId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> criteria = builder.createQuery(Message.class);
        Root<Dialog> dialog = criteria.from(Dialog.class);

        Join<Dialog, Message> messages = dialog.join(Dialog_.messages);

        criteria.select(messages).where(builder.equal(dialog.get(Dialog_.id), dialogId));
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public List<User> getDialogUsers(Long dialogId, String userEmail) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<Dialog> dialog = criteria.from(Dialog.class);
        Join<Dialog, User> users = dialog.join(Dialog_.dialogUsers);
        criteria.select(users).where(builder.and(
                builder.equal(dialog.get(Dialog_.id), dialogId),
                builder.notEqual(users.get(User_.email), userEmail)
        ));
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public List<User> getUnassociatedDialogUsers(List<User> avoidUsers) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> user = criteria.from(User.class);

        List<Predicate> restrictions = new ArrayList<>();
        for (User avoidUser : avoidUsers) {
            restrictions.add(builder.notEqual(user.get(User_.email), avoidUser.getEmail()));
        }
        criteria.select(user).where(builder.and(
                restrictions.toArray(new Predicate[restrictions.size()])));
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public List<Dialog> getUserDialogs(String userEmail) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Dialog> criteria = builder.createQuery(Dialog.class);
        Root<User> user = criteria.from(User.class);

        Join<User, Dialog> dialogs = user.join(User_.dialogs);
        criteria.select(dialogs).where(builder.equal(user.get(User_.email), userEmail));

        return entityManager.createQuery(criteria).getResultList();
    }
}
