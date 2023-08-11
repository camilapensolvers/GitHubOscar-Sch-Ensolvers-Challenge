import { reactive } from 'vue';
import { tagList } from './tagList';
import axios from "axios";

export const noteList = reactive({
    list : [],
    // filteredList : [],
    fetchActive(){
        axios.get("http://localhost:8080/note/active")
            .then(res =>{
                this.list = res.data.toSorted((a,b)=> a.lastEdited<=b.lastEdited?1:-1);
                tagList.fetchTags();
            });
    },
    fetchArchived(){
        axios.get("http://localhost:8080/note/archived")
            .then(res =>{
                this.list = res.data.toSorted((a,b)=> a.lastEdited<=b.lastEdited?1:-1);
                tagList.fetchTags();
            });
    },
    toggleArchived(id,archived){
        axios.patch(`http://localhost:8080/note/archived/${id}`)
            .then(res=>{
                let toggledNote = this.list.find(note=>note.id==id);
                toggledNote.archived = !toggledNote.archived;
                switch (archived) {
                    case false:
                        this.list = this.list.filter(note=> !note.archived);
                        break;
                    case true:
                        this.list = this.list.filter(note=> note.archived);
                        break;
                
                    default:
                        throw new Error("The value does not correspond to a archived status");
                        break;
                }
            })
    },
    deleteNote(id){
        axios.delete(`http://localhost:8080/note/delete/${id}`)
            .then(res=>{
                this.list = this.list.filter(note=> note.id !== id);
            });   
    },
    createNote(note){
        axios.post("http://localhost:8080/note/create",note)
            .then(res=>{
                this.fetchActive()
            })
    },
    editNote(newNote){
        axios.patch(`http://localhost:8080/note/update/${newNote.id}`,newNote)
            .then(res=>{
                if(newNote.archived){
                    this.fetchArchived();
                }else{
                    this.fetchActive();
                }
            }); 
    }
})