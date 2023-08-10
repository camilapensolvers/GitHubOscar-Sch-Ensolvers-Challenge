package com.ensolvers.notesolver.Services;

import com.ensolvers.notesolver.DTOs.NoteDTO;
import com.ensolvers.notesolver.Models.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    Note findNote(String title);
    Optional<Note> findNote(Integer id);
    void createNote(NoteDTO noteDTO);

    List<NoteDTO> getNotesAll();

    List<NoteDTO> getNotesActive();

    List<NoteDTO> getNotesArchived();
    void updateNote(Integer id, NoteDTO noteDTO);

    void deleteNote(Note note);

    void toggleIsArchived(Integer noteId);
}

