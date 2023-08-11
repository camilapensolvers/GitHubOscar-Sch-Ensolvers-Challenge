<!-- <script setup>
import { ref, computed } from 'vue';
import Note from "./Note.vue";
import { noteList } from "../globals/noteList.js";

const {selectedTag} = defineProps(['selectedTag'])

const activeTag = ref(selectedTag);

const filteredNoteList = computed(()=>{
    console.log("computed")
    if (activeTag.value != ""){
        return noteList.list.filter(note => {
            note.tagList.forEach(tag => {
                if (tag.name.toLowerCase().trim() === activeTag.value.toLowerCase().trim()){
                    return true
                }
            });
            return false;
        })
    }else{
        return noteList.list
    }
});

</script> -->
<script>
import Note from "./Note.vue";
import { noteList } from "../globals/noteList.js";

export default{
    components:{
        Note
    },
    props:["selectedTag"],
    data(){
        return{
            noteList,
        }
    },
    computed:{
        filteredNoteList(){
            console.log("computed")
            if (this.selectedTag != ""){
                return noteList.list.filter(note => {
                    let itMatched = false;
                    note.tagList.forEach(tag => {
                        if (tag.name.toLowerCase().trim() == this.selectedTag.toLowerCase().trim()){
                            itMatched = true;
                        }
                    });
                    return itMatched;
                })
            }else{
                return noteList.list
            }
        }
    }
}

</script>

<template>
    <section class="notes-container container-fluid row align-items-center justify-content-center">
        <template v-if="noteList.list.length" v-for="(note,index) in filteredNoteList">
            <Note :note="note" :notePropIndex="index"/>
        </template>
    </section>
</template>

<style scoped>
    .notes-container{
        gap: 2rem;
        width: 100%;
    }
</style>