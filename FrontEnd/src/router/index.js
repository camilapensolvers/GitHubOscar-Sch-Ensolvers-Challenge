import { createWebHistory, createRouter } from "vue-router";
import Home from "../views/Home.vue";
import MyNotes from "../views/MyNotes.vue";
import ArchivedNotes from "../views/ArchivedNotes.vue";

const routes = [
    {
        path: "/",
        name: "Home",
        component: Home,
    },
    {
        path: "/myNotes",
        name: "My Notes",
        component: MyNotes,
    },
    {
        path: "/archivedNotes",
        name: "Archived Notes",
        component: ArchivedNotes,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
