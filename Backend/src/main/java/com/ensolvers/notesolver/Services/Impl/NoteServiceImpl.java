package com.ensolvers.notesolver.Services.Impl;

import com.ensolvers.notesolver.DTOs.NoteDTO;
import com.ensolvers.notesolver.DTOs.TagDTO;
import com.ensolvers.notesolver.Models.Note;
import com.ensolvers.notesolver.Models.Tag;
import com.ensolvers.notesolver.Repositories.NoteRepository;
import com.ensolvers.notesolver.Repositories.TagRepository;
import com.ensolvers.notesolver.Services.NoteService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final TagRepository tagRepository;
    public NoteServiceImpl(NoteRepository noteRepository,
                                     TagRepository tagRepository){
        this.noteRepository = noteRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public Note findNote(String title) {
        return noteRepository.findByTitle(title);
    }

    @Override
    public Optional<Note> findNote(Integer id) {
        return noteRepository.findById(id);
    }

    @Override
    public void createNote(NoteDTO noteDTO) {

        Set<Tag> uniqueTagListToSave = checkForPersistedDuplicates(noteDTO);

        Note note = new Note(noteDTO.getTitle(), noteDTO.getContent(), uniqueTagListToSave);

        noteRepository.save(note);
    }

    @Override
    public List<NoteDTO> getNotesAll() {

        return noteRepository.findAll().stream().map(NoteDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<NoteDTO> getNotesActive() {

        return noteRepository.findNoteByIsArchived(false).stream().map(NoteDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<NoteDTO> getNotesArchived() {

        return noteRepository.findNoteByIsArchived(true).stream().map(NoteDTO::new).collect(Collectors.toList());
    }

    @Override
    public void updateNote(Integer noteId, NoteDTO noteDTO) {
        Note note = noteRepository.findById(noteId).get();

        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        note.setTagList(checkForPersistedDuplicates(noteDTO));
        noteRepository.save(note);
    }

    @Override
    public void toggleIsArchived(Integer noteId) {
        Note note = noteRepository.findById(noteId).get();
        note.toggleArchived();
        noteRepository.save(note);
    }

    @Override
    public void deleteNote(Note noteToDelete) {
        noteRepository.delete(noteToDelete);
    }


    private Set<Tag> checkForPersistedDuplicates(NoteDTO noteDTO){
        if (Objects.isNull(noteDTO.getTagList())){
            return null;
        }
        //Checking if the new Note to be created comes with tags that are
        //already persisted in the DB. If so it will replace in the Set the
        //repeated one for its persisted version so there is no repeating conflict

        //In the first part of the algorithm, it checks the Set<TagDTO> and replaces
        //any duplicated for a new DTO matching it persisted replica
        Set<TagDTO> uniqueTagsList = new HashSet<>();
        for (TagDTO tagDTO : noteDTO.getTagList()){
            Tag persistedTag = tagRepository.findByName(tagDTO.getName());
            if (Objects.nonNull(persistedTag)){
                uniqueTagsList.add(new TagDTO(persistedTag));
            }else{
                uniqueTagsList.add(tagDTO);
            }
        }
        //In the second part of the algorithm, while converting the DTO Set to a proper
        //Entity Set, it checks if the DTO matches an object persisted in the database,
        //if so, it saves that instance, otherwise it creates a new Tag object and stores it.
        // Now the Set is ready to be assigned to a Note and not causing any exception
        return uniqueTagsList.stream()
                .map(tagDTO -> {
                    Tag persistedTag = tagRepository.findByName(tagDTO.getName());
                    return persistedTag != null ? persistedTag : new Tag(tagDTO.getName());
                })
                .collect(Collectors.toSet());
    }
}
