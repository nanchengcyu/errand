import Vue from 'vue'
import VueRouter from 'vue-router'
import Manager from "@/views/Manager.vue";
import Home from "@/views/manager/Home.vue";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Manager',
        component: Manager,
        redirect:'/home',
        children:[
            {path:'home',name:'home',component:Home}
        ]
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('../views/Login.vue')
    },
    {
        path: '/register',
        name: 'Register',
        component: () => import('../views/Register.vue')
    },




]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
