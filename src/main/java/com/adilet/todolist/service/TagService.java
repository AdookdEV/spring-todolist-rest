package com.adilet.todolist.service;

import com.adilet.todolist.entity.Tag;
import com.adilet.todolist.exception.TagNotFoundException;
import com.adilet.todolist.repository.TagRepository;
import com.adilet.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Collection<Tag> findByUsername(String username) {
        return tagRepository.findByUsername(username);
    }

    public Tag findById(Integer id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new TagNotFoundException("Couldn't find tag with id " + id));
    }

    public Tag findByIdAndUsername(Integer id, String username) {
        return tagRepository.findTagByIdAndCreator_Username(id, username)
                .orElseThrow(() -> new TagNotFoundException(
                        String.format("User %s doesn't have tag with id %d", username, id)));
    }

    public Tag update(Integer id, String username, Tag tag) {
        Optional<Tag> t = tagRepository.findByUsername(username).stream().filter(tg -> tg.getId().equals(id)).findFirst();
        if (t.isEmpty()) {
            throw new TagNotFoundException("Couldn't find tag with id " + id);
        }
        Tag tagToSave = t.get();
        tagToSave.setName(tag.getName());
        return tagRepository.save(tagToSave);
    }

    public void deleteByIdAndUsername(Integer id, String username) {
        Tag tag = findByIdAndUsername(id, username);
        tagRepository.delete(tag);
    }

    public void deleteById(Integer id) {
        tagRepository.deleteById(id);
    }
}
