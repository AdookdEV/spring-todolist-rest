package com.adilet.todolist;

import com.adilet.todolist.entity.Role;
import com.adilet.todolist.entity.Tag;
import com.adilet.todolist.entity.TaskList;
import com.adilet.todolist.repository.RoleRepository;
import com.adilet.todolist.repository.TagRepository;
import com.adilet.todolist.repository.TaskListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class TodolistApplication {
	private final TagRepository tagRepository;
	private final TaskListRepository taskListRepository;
	private final RoleRepository roleRepository;

	private void createRoles() {
		Role userRole = new Role();
		userRole.setName("ROLE_USER");

		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");
		roleRepository.saveAll(List.of(userRole, adminRole));
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {

			createRoles();

			Tag tag1 = new Tag();
			tag1.setName("important");

			Tag tag2 = new Tag();
			tag2.setName("not important");

			tagRepository.saveAll(List.of(tag1, tag2));


			TaskList list1 = new TaskList();
			list1.setName("Inbox");

			TaskList list2 = new TaskList();
			list2.setName("University");

			taskListRepository.saveAll(List.of(list1, list2));



		};
	}

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}
}
