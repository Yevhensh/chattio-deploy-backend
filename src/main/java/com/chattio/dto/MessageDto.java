package com.chattio.dto;


import com.chattio.entity.Message;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class MessageDto {
    private Long id;
    private String value;
    private String dateTime;
    private UserDto user;

    public MessageDto() {
    }

    public MessageDto(Message message) {
        if (message.getId() != null) {
            this.id = message.getId();
        }
        this.value = message.getValue();
        if (message.getDateTime() != null) {
            this.dateTime = message.getDateTime().toString();
        }
        this.user = new UserDto(message.getUser());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageDto that = (MessageDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "id=" + id +
                '}';
    }
}
