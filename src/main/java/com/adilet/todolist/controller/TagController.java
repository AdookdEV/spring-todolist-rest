package com.adilet.todolist.controller;

import com.adilet.todolist.entity.Tag;
import com.adilet.todolist.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tag")
public class TagController {
    private final TagService tagService;

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable Integer id) {
        return tagService.findById(id);
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.findAll();
    }

    @PostMapping
    public Tag addTag(@RequestBody Tag tag) {
        return tagService.addTag(tag);
    }

    @PutMapping("/{id}")
    public Tag updateTag(@PathVariable Integer id,
                         @RequestBody Tag tag) {
        return tagService.update(id, tag);
    }

    @DeleteMapping("/{id}")
    public void deleteTagById(@PathVariable Integer id) {
        tagService.deleteById(id);
    }
}
