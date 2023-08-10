<script>
import axios from "axios";
export default{
    data (){
        return{
            info: "asdasdasdasd"
        }
    },
    mounted(){
        this.loadData();
    },
    methods:{
        loadData(){
            axios.get("http://localhost:8080/note/all")
            .then(res =>{
                this.info = res.data;
            });
        }
    }
}
</script>

<template>
    <header class="d-flex">
        <section class="hero-section d-flex flex-column justify-content-around align-items-center">
            <h1>Wellcome to EasyNotes</h1>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Minima, vero incidunt deleniti cum modi dolorum? Tenetur deserunt ullam excepturi deleniti, suscipit, blanditiis a tempora vel placeat sunt incidunt? Incidunt, inventore.</p>
            <button><router-link to="/myNotes">My Notes</router-link></button>
        </section>
        <section class="hero-image">
            <img src="" alt="">
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Numquam, aliquid molestiae tempore atque est earum quaerat? Reprehenderit exercitationem dolor quisquam debitis aut sunt labore nisi et, quaerat neque ipsam molestias.</p>
        </section>
    </header>
    <main>
        <h2>Esto es home</h2>
        <pre>{{ info }}</pre>
    </main>
    <footer>

    </footer>
</template>

<style scoped>
    header{
        padding: 2rem;
        background-color: #ccc;
        min-height: 60vh;
        width: 100%;
    }
    .hero-section{
        width: 60%;
        flex-shrink: 0;
    }
    .hero-section h1{
        font-size: 4rem;
    }
</style>