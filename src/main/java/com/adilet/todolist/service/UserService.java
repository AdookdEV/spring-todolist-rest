package com.adilet.todolist.service;

import com.adilet.todolist.entity.Role;
import com.adilet.todolist.entity.User;
import com.adilet.todolist.repository.RoleRepository;
import com.adilet.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User %s not found", username)
        ));
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .disabled(!user.getIsNonDisabled())
                .password(user.getPassword())
                .roles(user.getRoles().stream().map(Role::getName).toArray(String[]::new))
                .build();
    }

    public void createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException(String.format("User %s already exists", user.getUsername()));
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException(String.format("Email %s already exists", user.getEmail()));
        }
        user.setRoles(List.of(roleRepository.findRoleByName("ROLE_USER").get()));
        userRepository.save(user);
    }
}
