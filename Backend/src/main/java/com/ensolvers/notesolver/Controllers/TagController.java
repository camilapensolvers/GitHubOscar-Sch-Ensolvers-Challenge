package com.ensolvers.notesolver.Controllers;

import com.ensolvers.notesolver.Common.ApiResponse;
import com.ensolvers.notesolver.DTOs.TagDTO;
import com.ensolvers.notesolver.Models.Tag;
import com.ensolvers.notesolver.Services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;
    @Autowired
    public TagController(TagService tagService){
        this.tagService = tagService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createTag(@Valid @RequestBody TagDTO tagDTO){
        if (Objects.nonNull(tagService.findTag(tagDTO.getName()))){
            return new ResponseEntity<>(new ApiResponse(false, "This Tag already exists"), HttpStatus.CONFLICT);
        }
        tagService.createTag(tagDTO);
        return new ResponseEntity<>(new ApiResponse(true, "Tag created successfully"), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<TagDTO>> getTags(){
        List<TagDTO> tagList = tagService.getTags();
        return new ResponseEntity<>(tagList,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{tagId}")
    public ResponseEntity<ApiResponse> deleteTag(@PathVariable("tagId") Integer tagId){
        Tag tag = tagService.findTag(tagId).orElse(null);
        if (Objects.nonNull(tag)){
            tagService.deleteTag(tag);
            return new ResponseEntity<>(new ApiResponse(true,"Tag deleted successfully"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false,"There is no Tag matching with the id: " + tagId + " to be deleted"),HttpStatus.NOT_FOUND);
    }
}
