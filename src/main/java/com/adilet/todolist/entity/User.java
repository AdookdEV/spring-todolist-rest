package com.adilet.todolist.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String username;
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    private Boolean isNonDisabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;
}
