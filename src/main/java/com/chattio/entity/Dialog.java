package com.chattio.entity;

import com.chattio.constants.DialogConstants;
import com.chattio.constants.UserConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = DialogConstants.Entity.DIALOG_TABLE)
public class Dialog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DialogConstants.Entity.ID_DIALOG)
    private Long id;

    @Column(name = DialogConstants.Entity.DIALOG_NAME)
    private String dialogName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = DialogConstants.Entity.DIALOG_USERS_TALBE,
            joinColumns = @JoinColumn(name = DialogConstants.Entity.ID_DIALOG),
            inverseJoinColumns = @JoinColumn(name = UserConstants.Entity.ID_USER))
    private List<User> dialogUsers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "dialog")
    private List<Message> messages = new ArrayList<>();

    public Dialog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getDialogUsers() {
        return dialogUsers;
    }

    public void setDialogUsers(List<User> dialogUsers) {
        this.dialogUsers = dialogUsers;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Dialog{" +
                "id=" + id +
                ", dialogName='" + dialogName + '\'' +
                ", dialogUsers=" + dialogUsers +
                ", messages=" + messages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dialog dialog = (Dialog) o;
        return Objects.equals(id, dialog.id) &&
                Objects.equals(dialogName, dialog.dialogName) &&
                Objects.equals(dialogUsers, dialog.dialogUsers) &&
                Objects.equals(messages, dialog.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dialogName, dialogUsers, messages);
    }
}
