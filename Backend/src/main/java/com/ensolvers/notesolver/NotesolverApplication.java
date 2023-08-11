package com.ensolvers.notesolver;

import com.ensolvers.notesolver.DTOs.NoteDTO;
import com.ensolvers.notesolver.Models.Note;
import com.ensolvers.notesolver.Services.NoteService;
import com.ensolvers.notesolver.Services.TagService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NotesolverApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesolverApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner initData(NoteService noteService, TagService tagService) {
//		return (args) -> {
//			Note note1 = new Note("Todo List", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ex eaque amet praesentium asperiores dolores dicta molestias, facere laborum assumenda! Expedita inventore neque asperiores officia ullam recusandae nemo beatae rem cum.");
//			Note note2 = new Note("Grocery buys", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ex eaque amet praesentium asperiores dolores dicta molestias, facere laborum assumenda! Expedita inventore neque asperiores officia ullam recusandae nemo beatae rem cum.");
//			Note note3 = new Note("New story ideas", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ex eaque amet praesentium asperiores dolores dicta molestias, facere laborum assumenda! Expedita inventore neque asperiores officia ullam recusandae nemo beatae rem cum.");
//			Note note4 = new Note("Thoughts I had", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ex eaque amet praesentium asperiores dolores dicta molestias, facere laborum assumenda! Expedita inventore neque asperiores officia ullam recusandae nemo beatae rem cum.");
//
//			noteService.createNote(new NoteDTO(note1));
//			noteService.createNote(new NoteDTO(note2));
//			noteService.createNote(new NoteDTO(note3));
//			noteService.createNote(new NoteDTO(note4));
//
//		};
//	}
}
