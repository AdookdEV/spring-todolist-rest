package com.adilet.todolist.controller;

import com.adilet.todolist.entity.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {
    @GetMapping("/{id}")
    public Tag getTagById() {
        return null;
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return null;
    }

    @PostMapping
    public Tag addTag(@RequestBody Tag tag) {
        return null;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteTagById(@PathVariable String id) {
        return null;
    }
}
