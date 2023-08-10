package com.ensolvers.notesolver.Controllers;

import com.ensolvers.notesolver.Common.ApiResponse;
import com.ensolvers.notesolver.DTOs.NoteDTO;
import com.ensolvers.notesolver.Models.Note;
import com.ensolvers.notesolver.Services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;
    @Autowired
    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createNote (@Valid @RequestBody NoteDTO noteDTO){
        if (Objects.nonNull(noteService.findNote(noteDTO.getTitle()))){
            return new ResponseEntity<>(new ApiResponse(false,"You already have a note with that title"), HttpStatus.CONFLICT);
        }
        noteService.createNote(noteDTO);
        return new ResponseEntity<>(new ApiResponse(true,"Note created successfully"), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<NoteDTO>> getNotesAll(){
        List<NoteDTO> noteList = noteService.getNotesAll();
        return new ResponseEntity<>(noteList, HttpStatus.OK);
    }
    @GetMapping("/active")
    public ResponseEntity<List<NoteDTO>> getNotesActive(){
        List<NoteDTO> noteList = noteService.getNotesActive();
        return new ResponseEntity<>(noteList, HttpStatus.OK);
    }
    @GetMapping("/archived")
    public ResponseEntity<List<NoteDTO>> getNotesArchived(){
        List<NoteDTO> noteList = noteService.getNotesArchived();
        return new ResponseEntity<>(noteList, HttpStatus.OK);
    }
    @PatchMapping("/update/{noteId}")
    public ResponseEntity<ApiResponse> updateNote (@PathVariable("noteId") Integer noteId ,
                                                   @Valid @RequestBody NoteDTO noteDTO){
        if (Objects.nonNull(noteService.findNote(noteId))){
            noteService.updateNote(noteId , noteDTO);
            return new ResponseEntity<>(new ApiResponse(true,"Note has been updated successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false,"There is no Note matching with the id: " + noteId + " to be modified"), HttpStatus.NOT_FOUND);
    }
    @PatchMapping("/archived/{noteId}")
    public ResponseEntity<ApiResponse> toggleArchived(@PathVariable("noteId") Integer noteId){
        if (Objects.nonNull(noteService.findNote(noteId))){
            noteService.toggleIsArchived(noteId);
            return new ResponseEntity<>(new ApiResponse(true,"Note has been updated successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false,"There is no Note matching with the id: " + noteId + " to be modified"), HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete/{noteId}")
    public ResponseEntity<ApiResponse> deleteNote (@PathVariable("noteId") Integer noteId){
        Note note = noteService.findNote(noteId).orElse(null);
        if (Objects.nonNull(note)){
            noteService.deleteNote(note);
            return new ResponseEntity<>(new ApiResponse(true,"Note has been deleted successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false,"There is no Note matching with the id: " + noteId + " to be deleted"), HttpStatus.NOT_FOUND);
    }
}
