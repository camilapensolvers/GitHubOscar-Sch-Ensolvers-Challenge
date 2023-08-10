package com.ensolvers.notesolver.Services.Impl;

import com.ensolvers.notesolver.DTOs.TagDTO;
import com.ensolvers.notesolver.Models.Note;
import com.ensolvers.notesolver.Models.Tag;
import com.ensolvers.notesolver.Repositories.NoteRepository;
import com.ensolvers.notesolver.Repositories.TagRepository;
import com.ensolvers.notesolver.Services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final NoteRepository noteRepository;

    @Autowired
    public TagServiceImpl (TagRepository tagRepository, NoteRepository noteRepository){
        this.tagRepository = tagRepository;
        this.noteRepository = noteRepository;
    }

    @Override
    public Tag findTag(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public Optional<Tag> findTag(Integer id) {
        return tagRepository.findById(id);
    }

    @Override
    public List<TagDTO> getTags() {
        return tagRepository.findAll().stream().map(TagDTO::new).collect(Collectors.toList());
    }

    @Override
    public void createTag(TagDTO tagDTO) {
        Tag tag = new Tag(tagDTO.getName());
        tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Tag tagToDelete) {
        for (Note note : tagToDelete.getNotesList()){
            note.getTagList().remove(tagToDelete);
        }
        tagToDelete.getNotesList().clear();
        tagRepository.delete(tagToDelete);
    }
}

