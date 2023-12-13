package com.adilet.todolist.dto;

import com.adilet.todolist.entity.Role;
import com.adilet.todolist.entity.User;
import lombok.Value;
import org.springframework.beans.BeanUtils;

import java.util.Collection;

public enum UserDto {;
    private interface Id {Integer getId();}
    private interface Username { String getUsername();}
    private interface Email { String getEmail();}
    private interface IsNonDisabled {Boolean getIsNonDisabled();}
    private interface Password { String getPassword();}
    private interface Roles {Collection<Role> getRoles();}


    public enum Request {;
        @Value
        public static class Create implements Username, Email, Password {
            String username;
            String email;
            String password;

            public static User toUser(UserDto.Request.Create dto) {
                User u = new User();
                BeanUtils.copyProperties(dto, u);
                return u;
            }
        }

        @Value
        public static class Login implements Username, Password {
            String username;
            String password;
        }
    }

    public enum Response {;
        @Value
        public static class Private implements Id, Username, Email, IsNonDisabled, Roles {
            Integer id;
            String username;
            String email;
            Boolean isNonDisabled;
            Collection<Role> roles;

            public static User toUser(UserDto.Response.Private dto) {
                User u = new User();
                u.setId(dto.getId());
                u.setUsername(dto.getUsername());
                u.setEmail(dto.getEmail());
                u.setIsNonDisabled(dto.getIsNonDisabled());
                u.setRoles(dto.getRoles());
                return u;
            }

            public static UserDto.Response.Private toDto(User u) {
                return new Private(
                        u.getId(),
                        u.getUsername(),
                        u.getEmail(),
                        u.getIsNonDisabled(),
                        u.getRoles());
            }
        }

        @Value
        public static class Public implements Id, Username, Email {
            Integer id;
            String username;
            String email;

            public static User toUser(UserDto.Response.Public dto) {
                User u = new User();
                u.setId(dto.getId());
                u.setUsername(dto.getUsername());
                u.setEmail(dto.getEmail());
                return u;
            }

            public static UserDto.Response.Public toDto(User u) {
                return new Public(
                        u.getId(),
                        u.getUsername(),
                        u.getEmail());
            }
        }
    }
}
