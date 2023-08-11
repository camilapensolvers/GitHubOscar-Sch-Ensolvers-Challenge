<script>
import axios from "axios";
export default{
    data (){
        return{
            
        }
    },
}
</script>

<template>
    <header class="note-color-2">
        <section class="hero-section d-flex flex-column justify-content-around align-items-center">
            <img src="../assets/Nsolver-logo.png" alt="Page Logo">
            <div class="p-section">
                <p>Unleash Your Creativity with <span>NoteSolver</span></p>
            </div>
            <router-link to="/myNotes"><button class="btn button-primary">Sign Up</button></router-link>
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
        min-height: 60vh;
        width: 100%;
        box-shadow: 0rem 1rem 1rem #0005,
        0rem 0rem 0rem 3px #0003 inset;
    }
    img{
        width: 100%;
    }
    .hero-section{
        width: 100%;
        gap: 1rem;
        margin: 0 auto;
        max-width: 1000px;
    }
    .p-section{
        padding: 1em;
    }
    .p-section p{
        text-align: center;
        font-size: 2.5rem;
        font-weight: 700;
        margin: 0;
        border-bottom: 3px dashed black;
    }
    span{
        font-family: 'Joti One', cursive;
    }
    button{
        font-size: 3rem;
    }
</style>