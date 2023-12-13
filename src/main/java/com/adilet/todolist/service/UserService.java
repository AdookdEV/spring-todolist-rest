package com.adilet.todolist.service;

import com.adilet.todolist.entity.Role;
import com.adilet.todolist.entity.TaskList;
import com.adilet.todolist.entity.User;
import com.adilet.todolist.exception.RegistrationException;
import com.adilet.todolist.repository.RoleRepository;
import com.adilet.todolist.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final TaskListService taskListService;

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

    @Transactional
    public void createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RegistrationException(String.format("User %s already exists", user.getUsername()));
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RegistrationException(String.format("Email %s already exists", user.getEmail()));
        }
        Role userRole = roleRepository.findRoleByName("USER").orElseThrow();
        user.setRoles(List.of(userRole));
        TaskList defaultTaskList = new TaskList();
        defaultTaskList.setName("Task list");
        defaultTaskList.setCreator(userRepository.save(user));
        taskListService.addTaskList(defaultTaskList, user.getUsername());
    }

    public Page<User> findAll(@Min(0) Integer offset,
                              @Min(1) @Max(100) Integer limit) {
        return userRepository.findAll(PageRequest.of(offset, limit));
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User with id %d not found", id)
        ));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User %s not found", username)
        ));
    }

    @Transactional
    public void disableUser(Integer id) {
        userRepository.disableById(id);
    }

    @Transactional
    public void enableUser(Integer id) {
        userRepository.enableById(id);
    }
}
