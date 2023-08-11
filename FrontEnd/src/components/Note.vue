<script setup>
import {activeNote} from "../globals/activeNote.js";
import { noteList } from '../globals/noteList.js';
import { defineProps } from 'vue';

const {note, notePropIndex} = defineProps(['note', 'notePropIndex'])

function handleDateFormating(rawDate){
            let [date,time] = rawDate.split("T");
            time = time.slice(0,5);
            return `${date} ${time}`;
        }

function manageActive(noteToActivate, noteIndex){
            activeNote.note = noteToActivate;
            activeNote.index = noteIndex;
            console.log(activeNote.note)
        }
function manageActiveMock(noteToActivate, noteIndex){
            activeNote.note = {...noteToActivate};
            activeNote.index = noteIndex;
            console.log(activeNote.note)
        }
</script>

<template>
    <article v-if="note" :class="'note col-11 col-sm-5 col-md-4 col-lg-3 '+`note-color-${notePropIndex%6}`">
        <h4>{{ note.title }}</h4>
        <p class="note-content">{{ note.content }}</p>
        <p class="note-lastEdited"><b>Last edited:</b> {{ handleDateFormating(note.lastEdited) }}</p>


        <div class="note-icons d-flex justify-content-between">
            <template v-if="!note.archived">
                <i class="fa-solid fa-box-archive fa-xl" @click="noteList.toggleArchived(note.id, note.archived)" data-toggle="tooltip" data-placement="top" title="Archive note"></i>
            </template>
            <template v-else>
                <i class="fa-solid fa-box-open fa-xl" @click="noteList.toggleArchived(note.id, note.archived)" data-toggle="tooltip" data-placement="top" title="Retrieve note"></i>
            </template>

            <div class="d-flex align-items-start" data-toggle="tooltip" data-placement="top" title="Edit note">
                <i @click="manageActiveMock(note,notePropIndex)" class="fa-solid fa-marker fa-xl" data-toggle="modal" data-target="#editNoteModal"></i>
            </div>

            <div class="d-flex align-items-start" data-toggle="tooltip" data-placement="top" title="Delete note">
                <i @click="manageActive(note,notePropIndex)" class="fa-regular fa-trash-can fa-xl" data-toggle="modal" data-target="#archiveConfirmModal"></i>
            </div>
        </div>
    </article>

</template>

<style scoped>
    .note{
        aspect-ratio: 1/1;
        padding: .5rem;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        align-items: center;
        border-radius: .3rem;
        /* box-shadow: 1rem 1rem 1rem #0005,
        0rem 0rem 0rem 3px #0003 inset; */
        transition: all .3s;
    }
    .note:hover{
        scale: 1.05;
        box-shadow: 1.3rem 1.3rem 1.5rem #0005,
        0rem 0rem 0rem 3px #0003 inset;
    }

    .note h4{
        /* width: 100%; */
        text-align: center;
        font-weight: bolder;
        border-bottom: 3px dashed #0008;
    }
    .note-content{
        height: 60%;
        width: 90%;
        overflow-y: scroll;
        /* -ms-overflow-style: none; */
        /* scrollbar-width: none; */
        scrollbar-color: #0003 transparent;
        scrollbar-width: thin;
    }
    ::-webkit-scrollbar {
        width: 7px;
    }
    ::-webkit-scrollbar-thumb {
        background: #0003;
    } 
    .note-lastEdited{
        /* width: 100%; */
        padding: .3em .5em;
        text-align: center;
        border: 2px dashed #0008;
    }
    .note-icons{
        width: 80%;
        height: 1rem;
    }
    i{
        cursor: pointer;
    }
    
</style>