package com.chattio.repository;

import com.chattio.dto.UserDto;
import com.chattio.entity.Dialog;
import com.chattio.entity.Message;
import com.chattio.entity.User;

import java.util.List;

public interface DialogRepository extends BaseRepository<Dialog> {

    List<Dialog> getUserDialogs(String userEmail);

    void createDialog(User firstUser, User secondUser);

    List<Message> findMessagesByDialog(Long dialogId);

    List<User> getDialogUsers(Long dialogId, String userEmail);

    List<User> getUnassociatedDialogUsers(List<User> avoidUsers);
}
