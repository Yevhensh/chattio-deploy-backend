package com.chattio.entity;

import com.chattio.constants.DialogConstants;
import com.chattio.constants.MessageConstants;
import com.chattio.constants.UserConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = MessageConstants.Entity.MESSAGE_TABLE)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = MessageConstants.Entity.ID_MESSAGE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = DialogConstants.Entity.ID_DIALOG)
    @JsonIgnore
    private Dialog dialog;

    @Column(name = MessageConstants.Entity.VALUE)
    private String value;

    @Column(name = MessageConstants.Entity.DATE_TIME)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = UserConstants.Entity.ID_USER)
    private User user;

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&
                Objects.equals(value, message.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }


}
