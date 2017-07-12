package com.chattio.dto;

import java.util.List;
import java.util.Objects;

public class DialogDto {
    private Long id;
    private String dialogName;
    private List<UserDto> dialogUsers;

    public DialogDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserDto> getDialogUsers() {
        return dialogUsers;
    }

    public void setDialogUsers(List<UserDto> dialogUsers) {
        this.dialogUsers = dialogUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DialogDto dialogDto = (DialogDto) o;
        return Objects.equals(id, dialogDto.id) &&
                Objects.equals(dialogUsers, dialogDto.dialogUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dialogUsers);
    }

    @Override
    public String toString() {
        return "DialogDto{" +
                "id=" + id +
                ", dialogName='" + dialogName + '\'' +
                '}';
    }
}
