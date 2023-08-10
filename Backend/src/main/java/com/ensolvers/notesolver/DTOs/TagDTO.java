package com.ensolvers.notesolver.DTOs;

import com.ensolvers.notesolver.Models.Tag;

public class TagDTO {
    private Integer id;
    private String name;

    public TagDTO() {}
    public TagDTO(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
    }

    public TagDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
