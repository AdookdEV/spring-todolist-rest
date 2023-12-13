package com.adilet.todolist.controller;

import com.adilet.todolist.entity.Tag;
import com.adilet.todolist.service.TagService;
import com.adilet.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags")
@Slf4j
public class TagController {
    private final TagService tagService;
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Integer id, Principal principal) {
        return ResponseEntity.ok(tagService.findByIdAndUsername(id, principal.getName()));
    }

    @GetMapping
    public ResponseEntity<Collection<Tag>> getTagsByUsername() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        return ResponseEntity.ok(tagService.findByUsername(username));
    }

    @PostMapping
    public ResponseEntity<Tag> addTag(@RequestBody Tag tag) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        log.info("Create tag for User: {}", username);
        tag.setCreator(userService.findByUsername(username));
        return new ResponseEntity<>(tagService.addTag(tag), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Integer id,
                         @RequestBody Tag tag) {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        return ResponseEntity.ok(tagService.update(id, username, tag));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTagByIdAndUsername(@PathVariable Integer id, Principal principal) {
        tagService.deleteByIdAndUsername(id, principal.getName());
        return ResponseEntity.noContent().build();
    }
}
