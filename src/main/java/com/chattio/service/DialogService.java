package com.chattio.service;

import com.chattio.dto.DialogDto;
import com.chattio.dto.MessageDto;
import com.chattio.dto.UserDto;
import com.chattio.entity.Message;

import java.util.List;

public interface DialogService {

    MessageDto sendMessage(String messageValue, Long dialogId, String userEmail);

    List<DialogDto> getUserDialogs(String userEmail);

    void createDialogWithUser(String firstUserEmail, String secondUserEmail);

    List<MessageDto> findMessagesByDialog(Long dialogId);

    List<UserDto> getDialogUsers(Long dialogId, String userEmail);

    void addUserToDialog(String userEmail, Long dialogId);

    List<UserDto> getUnassociatedDialogUsers(Long dialogId);
}

