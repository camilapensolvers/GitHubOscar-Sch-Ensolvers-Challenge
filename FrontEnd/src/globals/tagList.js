import { reactive } from 'vue';
import axios from "axios";

export const tagList = reactive({
    list : [],
    fetchTags(){
        axios.get("http://localhost:8080/tag/")
            .then(res =>{
                this.list = res.data.toSorted();
            });
    },
})