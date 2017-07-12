package com.chattio.entity;

import com.chattio.constants.DialogConstants;
import com.chattio.constants.UserConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = UserConstants.Entity.USER_TABLE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = UserConstants.Entity.ID_USER)
    private Long id;

    @NotNull
    @Column(name = UserConstants.Entity.FULL_NAME)
    private String fullName;

    @NotNull
    @Column(name = UserConstants.Entity.EMAIL)
    private String email;

    @NotNull
    @Column(name = UserConstants.Entity.ACTIVE)
    private boolean active;

    @NotNull
    @Column(name = UserConstants.Entity.PASSWORD)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = DialogConstants.Entity.USER)
    private List<Message> messages;

    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = UserConstants.Entity.USER_ROLES,
            joinColumns = @JoinColumn(name = UserConstants.Entity.USER))
    @Column(name = UserConstants.Entity.ROLE)
    @Enumerated(EnumType.ORDINAL)
    private List<Role> roles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = DialogConstants.Entity.DIALOG_USERS)
    @JsonIgnore
    private List<Dialog> dialogs = new ArrayList<>();

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Dialog> getDialogs() {
        return dialogs;
    }

    public void setDialogs(List<Dialog> dialogs) {
        this.dialogs = dialogs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(fullName, user.fullName) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

