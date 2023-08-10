package com.ensolvers.notesolver.Services;

import com.ensolvers.notesolver.DTOs.TagDTO;
import com.ensolvers.notesolver.Models.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {
    Tag findTag(String name);
    Optional<Tag> findTag(Integer id);

    void createTag(TagDTO tagDTO);

    List<TagDTO> getTags();

    void deleteTag(Tag tag);
}
