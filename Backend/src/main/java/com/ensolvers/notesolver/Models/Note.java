package com.ensolvers.notesolver.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="Notes")
public class Note {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator= "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;

    @Column(name="Title")
    @NotBlank(message="The title cannot be blank")
//    @Size(max = 25 , message="The title is too long")
    private String title;
    @Column(name="Content")
    @NotBlank(message="The content cannot be blank")
    private String content;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Note_Tags",
            joinColumns = @JoinColumn(name = "note_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tagList;
    @Column(name="Is_Archived")
    private Boolean isArchived = false;
    @Column(name="Last_Edited")
    private LocalDateTime lastEdited;

    public Note() {
    }

    public Note(@NotBlank String title , @NotBlank String content) {
        this.title = title;
        this.content = content;
        this.lastEdited = LocalDateTime.now();
    }

    public Note(@NotBlank String title ,@NotBlank String content, @NotNull Set<Tag> tagList) {
        this.title = title;
        this.content = content;
        this.tagList = tagList;
        this.isArchived = false;
        this.lastEdited = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.lastEdited = LocalDateTime.now();
    }

    public String getContent() {
        return content;
    }

    public void setContent(@NotBlank String content) {
        this.content = content;
        this.lastEdited = LocalDateTime.now();
    }

    public Set<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(Set<Tag> tagList) {
        this.tagList = tagList;
        this.lastEdited = LocalDateTime.now();
    }
    public void addTags(Set<Tag> tagList) {
        this.tagList.addAll(tagList);
        this.lastEdited = LocalDateTime.now();
    }
    public void addTags(Tag tag) {
        this.tagList.add(tag);
        this.lastEdited = LocalDateTime.now();
    }

    public Boolean getArchived() {
        return isArchived;
    }

    public void toggleArchived() {
        this.isArchived = !this.isArchived;
        this.lastEdited = LocalDateTime.now();
    }

    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", name='" + title + '\'' +
                ", content='" + content + '\'' +
                ", tagList=" + tagList +
                ", isArchived=" + isArchived +
                ", lastEdited=" + lastEdited +
                '}';
    }
}