package com.ensolvers.notesolver.DTOs;

import com.ensolvers.notesolver.Models.Note;
import com.ensolvers.notesolver.Models.Tag;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class NoteDTO {
    private Integer id;
    @NotBlank(message = "The title cannot be blank")
//    @Size(max = 25 , message="The title is too long")
    private String title;
    @NotBlank(message = "The content cannot be blank")
    private String content;
    private Set<TagDTO> tagList;
    private Boolean isArchived = false;
    private LocalDateTime lastEdited;

    public NoteDTO() {
    }

    public NoteDTO(Note note) {
        this.id = note.getId();
        this.title = note.getTitle();
        this.content = note.getContent();
        if (Objects.nonNull(tagList)) {
            this.tagList = note.getTagList().stream().map(TagDTO::new).collect(Collectors.toSet());
        }
        this.isArchived = note.getArchived();
        this.lastEdited = note.getLastEdited();
    }

    public NoteDTO(Integer id, String title, String content, Set<Tag> tagList, Boolean isArchived, LocalDateTime lastEdited) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tagList = tagList.stream().map(TagDTO::new).collect(Collectors.toSet());
        ;
        this.isArchived = isArchived;
        this.lastEdited = lastEdited;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<TagDTO> getTagList() {
        return tagList;
    }

    public void setTagList(Set<TagDTO> tagList) {
        this.tagList = tagList;
    }

    public Boolean getArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(LocalDateTime lastEdited) {
        this.lastEdited = lastEdited;
    }
}
