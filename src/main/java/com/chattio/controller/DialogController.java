package com.chattio.controller;

import com.chattio.dto.DialogDto;
import com.chattio.dto.MessageDto;
import com.chattio.dto.UserDto;
import com.chattio.entity.Message;
import com.chattio.service.DialogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class DialogController {

    @Autowired
    private DialogService dialogService;

    @GetMapping("/dialogs")
    public List<DialogDto> fetchUserDialogs(Principal principal) {
        return dialogService.getUserDialogs(principal.getName());
    }

    @PutMapping("/dialogs/{dialogId}/users")
    public void addUserToDialog(@RequestBody String userEmail, @PathVariable Long dialogId) {
        dialogService.addUserToDialog(userEmail, dialogId);
    }

    @PostMapping("/dialogs")
    public void createDialog(@RequestBody String userEmail, Principal principal) {
        dialogService.createDialogWithUser(userEmail, principal.getName());
    }

    @GetMapping("/dialog/{dialogId}")
    public List<MessageDto> fetchMessages(@PathVariable Long dialogId) {
        return dialogService.findMessagesByDialog(dialogId);
    }

    @GetMapping("/dialog/{dialogId}/users")
    public List<UserDto> fetchDialogUsers(@PathVariable Long dialogId, Principal principal) {
        return dialogService.getDialogUsers(dialogId, principal.getName());
    }


}
