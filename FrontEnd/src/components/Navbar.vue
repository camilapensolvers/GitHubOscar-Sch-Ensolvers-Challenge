<script>
export default{
    data(){
        return{

        }
    }
}
</script>

<template>
    <nav class="d-flex justify-content-around flex-wrap">
        <div class="links d-flex">
            <router-link class="link" to="/myNotes">My Notes</router-link>
            <router-link class="link" to="/archivedNotes">Archived Notes</router-link>
        </div>
        <router-link class="link" to="#">Sign out</router-link>
    </nav>
</template>

<style scoped>
    nav{
        gap: 2rem;
        padding: .5rem;
        background-color: #cbaacb;
        box-shadow: 0px 0px 5px 3px #0003 inset, 0em .3em 1em #0005;
    }
    .links{
        gap: 2rem;
    }
    .link{
        color: black;
        font-size: 1.5rem;
        font-weight: bolder;
        font-family: 'Joti One', cursive;
    }
</style>