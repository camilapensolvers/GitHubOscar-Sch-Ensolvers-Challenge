<script setup>
import { RouterView } from 'vue-router';
import { activeNote } from './globals/activeNote';
import { noteList } from './globals/noteList';
import {ref} from "vue";


let tagToAdd = "";

function handleAddTag(){
    if (tagToAdd.trim()!="" && 
        !activeNote.note.tagList.some(tag => tag.name.trim().toLowerCase()===tagToAdd.trim().toLowerCase())){
            activeNote.note.tagList = [...activeNote.note.tagList,{name : tagToAdd}]
        }
    tagToAdd="";
}
function handleRemoveTag(name){
    let tagIndex = activeNote.note.tagList.findIndex(tag=>tag.name == name);
    if (tagIndex != -1) {
        activeNote.note.tagList.splice(tagIndex,1);
    }
}


</script>

<template>
  <RouterView/>







  <div class="modal fade" id="archiveConfirmModal" tabindex="-1" role="dialog" aria-labelledby="archiveConfirmModalTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div :class="'modal-content '+`note-color-${activeNote.index%6}`">
            <div class="modal-header">
                <h5 class="modal-title" id="archiveConfirmModalTitle">Delete Note</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to <b>DELETE</b> <span class="span-highlight">{{`"${activeNote.note?.title}"`}}</span>?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn button-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn button-danger" data-dismiss="modal" @click="noteList.deleteNote(activeNote.note.id)">Delete</button>
            </div>
            </div>
        </div>
    </div>




    <div v-if="activeNote.note" class="modal fade" id="editNoteModal" tabindex="-1" role="dialog" aria-labelledby="editNoteModalTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
            <div :class="'modal-content '+`note-color-${activeNote.index%6}`">
            <div class="modal-header">
                <h5 class="modal-title" id="editNoteModalTitle">{{ activeNote.note.id?"Edit note":"Create note" }}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body edit-modal-body d-flex flex-column">
                <label class="align-items-center">
                    <h5>Title</h5>
                    <input type="text" name="title" v-model="activeNote.note.title">
                </label>
                <span class="align-self-end character-count">{{activeNote.note.content.length}}/500</span>
                <label>
                    <h5>Content</h5>
                    <textarea name="" id="" cols="30" rows="10" maxlength="500" v-model="activeNote.note.content"></textarea>
                </label>
                <label>
                    <h5>Categories</h5>
                    <div class="tags-container d-flex flex-column align-items-end">
                        <div class="tags-display d-flex flex-column align-items-start">
                            <template v-if="activeNote.note?.tagList.length" v-for="(tag,index) in activeNote.note.tagList">
                                <div :class="'tag d-flex align-items-center '+`note-color-${index%6}`">
                                    <i class="fa-solid fa-tag fa-xl"></i>
                                    <h3>{{ tag.name }}</h3>
                                    <i class="fa-solid fa-xmark fa-xl tag-erase" @click="handleRemoveTag(tag.name)" data-toggle="tooltip" data-placement="top" title="Remove tag"></i>
                                </div>
                            </template>
                        </div>
                        <div class="tags-add-container d-flex justify-content-between">
                            <input type="text" name="tagName" v-model="tagToAdd">
                            <button class="btn button-add" @click="handleAddTag">Add</button>
                        </div>
                    </div>
                </label>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn button-secondary" data-dismiss="modal">Close</button>
                <template v-if="activeNote.note.id">
                    <button type="button" class="btn button-primary" data-dismiss="modal" @click="noteList.editNote(activeNote.note)">Save changes</button>
                </template>
                <template v-else>
                    <button type="button" class="btn button-primary" data-dismiss="modal" @click="noteList.createNote(activeNote.note)">Create note</button>
                </template>
            </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
    .modal-header{
        border: none;
    }
    .modal-title{
        border-bottom: 3px dashed #0008;
        font-family: var(--note-title);
        font-size: 1.5rem !important;
    }
    .edit-modal-body{
        gap: .5rem;
    }
    input,textarea{
        background: #fff5 ;
        border: none;
        width: 100%;
        border: 2px dashed #0008;
        padding: .3em .5em;
    }
    input{
        font-family: var(--note-title);
        font-weight: bolder;
        font-size: 1.3rem;
    }
    .character-count{
        font-family: var(--note-content);
        font-size: 1.2rem;
        font-weight: bolder;
    }
    textarea{
        resize: none;
        font-family: var(--note-content);
        font-weight: 500;
        font-size: 1.2rem;
    }
    label{
        display: flex;
        justify-content: space-between;
        margin: 0;
    }
    label>h5{
        width: 30%;
    }
    .tags-container{
        width: 100%;
        gap: 1rem;
    }
    .tags-display{
        width: 100%;
        padding: .5rem;
        height: 10rem;
        background: #fff5 ;
        border: 2px dashed #0008;
        overflow-y: scroll;
        gap: .5rem;
        scrollbar-color: #0003 transparent;
        scrollbar-width: thin;
    }
    ::-webkit-scrollbar {
        width: 7px;
    }
    ::-webkit-scrollbar-thumb {
        background: #0003;
    } 
    .tags-add-container{
        width: 100%;
    }
    .tags-add-container input{
        max-width: 25rem;
    }
    .note-lastEdited{
        padding: .3em .5em;
        text-align: center;
        border: 2px dashed #0008;
    }
    .tags-add-container button{
        width: 8rem;
        margin-right: 2rem;
    }
    .tag{
        gap: 1rem;
        border: 2px solid #0008;
        padding: .3em .5em;
        box-shadow: .2rem .2rem .2rem #0005,
        0rem 0rem 0rem 3px #0003 inset;
        font-family: var(--note-title);
    }
    .tag-erase{
        cursor: pointer;
    }
    .tag h3{
        margin: 0;
    }
    .modal-footer{
        border: none;
    }
</style>
