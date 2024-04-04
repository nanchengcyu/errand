import Vue from 'vue'
import VueRouter from 'vue-router'
import Manager from "@/views/Manager.vue";
import Home from "@/views/manager/Home.vue";
import User from "@/views/manager/User.vue";
import Person from "@/views/manager/Person.vue";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Manager',
        component: Manager,
        redirect: '/home',
        children: [
            {path: 'home', name: 'home', component: Home},
            {path: 'user', name: 'user', component: User},
            {path: 'person', name: 'person', component: Person},
            {
                path: '/403',
                name: 'Auth',
                component: () => import('../views/manager/Auth.vue')
            },

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
//路由守卫
router.beforeEach((to, from, next) => {
    //to 是到达的路由信息
    //form 是来自哪里
    //next是帮助我们跳转路由的函数
    let adminPath = ['/user']
    let user = JSON.parse(localStorage.getItem('ncy-user') || '{}')

    if (user.role !== '管理员' && adminPath.includes(to.path)) {
        next('/403')
    } else {
        next()
    }


})
export default router
