package com.adilet.todolist.service;

import com.adilet.todolist.entity.Tag;
import com.adilet.todolist.exception.TagNotFoundException;
import com.adilet.todolist.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag findById(Integer id) {
        return tagRepository.findById(id)
                .orElseThrow(TagNotFoundException::new);
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public Tag update(Integer id, Tag tag) {
        tag.setId(id);
        return tagRepository.save(tag);
    }

    public void deleteById(Integer id) {
        tagRepository.deleteById(id);
    }
}
