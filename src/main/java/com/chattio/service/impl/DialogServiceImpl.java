package com.chattio.service.impl;

import com.chattio.dto.DialogDto;
import com.chattio.dto.MessageDto;
import com.chattio.dto.UserDto;
import com.chattio.entity.Dialog;
import com.chattio.entity.Message;
import com.chattio.entity.User;
import com.chattio.repository.DialogRepository;
import com.chattio.repository.UserRepository;
import com.chattio.service.DialogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DialogServiceImpl implements DialogService {

    @Autowired
    private DialogRepository dialogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MessageDto sendMessage(String messageValue, Long dialogId, String userEmail) {
        Message message = new Message();
        message.setUser(userRepository.findByEmail(userEmail));
        message.setDateTime(LocalDateTime.now());
        message.setValue(messageValue);

        Dialog dialog = dialogRepository.findById(dialogId);
        message.setDialog(dialog);

        dialog.getMessages().add(message);
        dialogRepository.update(dialog);
        return new MessageDto(message);
    }

    @Override
    public List<DialogDto> getUserDialogs(String userEmail) {
        return dialogRepository.getUserDialogs(userEmail)
                .stream()
                .map(dialog -> modelMapper.map(dialog, DialogDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createDialogWithUser(String firstUserEmail, String secondUserEmail) {
        Dialog dialog = new Dialog();
        dialog.setDialogUsers(Arrays.asList(userRepository.findByEmail(firstUserEmail),
                userRepository.findByEmail(secondUserEmail)));
        dialogRepository.create(dialog);
    }

    @Override
    public List<MessageDto> findMessagesByDialog(Long dialogId) {
        return dialogRepository.findMessagesByDialog(dialogId)
                .stream()
                .map(MessageDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getDialogUsers(Long dialogId, String userEmail) {
        return dialogRepository.getDialogUsers(dialogId, userEmail)
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addUserToDialog(String userEmail, Long dialogId) {
        Dialog dialog = dialogRepository.findById(dialogId);
        dialog.getDialogUsers().add(userRepository.findByEmail(userEmail));
        dialogRepository.update(dialog);
    }

    @Override
    public List<UserDto> getUnassociatedDialogUsers(Long dialogId) {
        List<User> avoidUsers = dialogRepository.findById(dialogId).getDialogUsers();
        return dialogRepository.getUnassociatedDialogUsers(avoidUsers)
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
}
