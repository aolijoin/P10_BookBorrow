import Vue from 'vue'
import VueRouter from 'vue-router'
import bookList from "@/components/bookList.vue";
import Book from "@/components/book.vue";
import borrowedInfo from "@/components/borrowedInfo.vue";
import borrowedList from "@/components/borrowedList.vue";

Vue.use(VueRouter)

const routes = [
    {
        path: '/book',
        name: 'book',
        component: Book
    },
    {
        path: '/',
        name: 'bookList',
        component: bookList
    }, {
        path: '/borrowedInfo',
        name: 'bookList',
        component: borrowedInfo
    }, {
        path: '/borrowedList',
        name: 'borrowedList',
        component: borrowedList
    },
]

const router = new VueRouter({
    routes
})

export default router
