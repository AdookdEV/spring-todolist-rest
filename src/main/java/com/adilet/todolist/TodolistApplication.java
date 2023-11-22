package com.adilet.todolist;

import com.adilet.todolist.entity.Role;
import com.adilet.todolist.entity.Tag;
import com.adilet.todolist.entity.TaskList;
import com.adilet.todolist.entity.User;
import com.adilet.todolist.repository.RoleRepository;
import com.adilet.todolist.repository.TagRepository;
import com.adilet.todolist.repository.TaskListRepository;
import com.adilet.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class TodolistApplication {
	private final TagRepository tagRepository;
	private final TaskListRepository taskListRepository;
	private final RoleRepository roleRepository;
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	private void createRoles() {
		Role userRole = new Role();
		userRole.setName("USER");

		Role adminRole = new Role();
		adminRole.setName("ADMIN");
		roleRepository.saveAll(List.of(userRole, adminRole));
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {

			createRoles();
			Role adminRole = roleRepository.findRoleByName("ADMIN").get();
			Role userRole = roleRepository.findRoleByName("USER").get();
			User user = new User();
			user.setUsername("Adilet");
			user.setPassword(passwordEncoder.encode("123"));
			user.setEmail("test@email.com");
			user.setRoles(List.of(userRole));
			userService.createUser(user);

			User admin = new User();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("123"));
			admin.setEmail("admin@email.com");
			admin.setRoles(List.of(adminRole));
			userService.createUser(admin);


			Tag tag1 = new Tag();
			tag1.setName("important");
			tag1.setCreator(user);

			Tag tag2 = new Tag();
			tag2.setName("not important");
			tag2.setCreator(user);

			tagRepository.saveAll(List.of(tag1, tag2));


			TaskList list1 = new TaskList();
			list1.setName("Inbox");
			list1.setCreator(user);

			TaskList list2 = new TaskList();
			list2.setName("University");
			list2.setCreator(user);

			taskListRepository.saveAll(List.of(list1, list2));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}
}
