import Vue from 'vue'
import VueRouter from 'vue-router'
import Manager from "@/views/Manager.vue";
import Home from "@/views/manager/Home.vue";
import User from "@/views/manager/User.vue";
import Person from "@/views/manager/Person.vue";
import Logs from "@/views/manager/Logs.vue";
import Orders from "@/views/manager/Orders.vue";
import Address from "@/views/manager/Address.vue";
import Certification from "@/views/manager/Certification.vue";

// 解决重复点击导航时，控制台出现报错  ---亦可在跳转时做判断
const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (to) {
    return VueRouterPush.call(this, to).catch(err => err)
}
Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Manager',
        component: Manager,
        redirect: '/home',
        children: [
            {path: 'home', name: 'home',meta:{name:'系统首页'}, component: Home},
            {path: 'user', name: 'user', meta:{name:'用户管理'},component: User},
            {path: 'orders', name: 'orders', meta:{name:'订单管理'},component: Orders},
            {path: 'address', name: 'address', meta:{name:'地址管理'},component: Address},
            {path: 'certification', name: 'certification', meta:{name:'骑手管理'},component: Certification},
            {path: 'person', name: 'person', meta:{name:'个人信息'},component: Person},
            {path: 'logs', name: 'logs', meta:{name:'日志信息'},component: Logs},
            {
                path: '/403',
                name: 'Auth',
                meta:{name:'无权限'},
                component: () => import('../views/manager/Auth.vue')
            },

        ]
    },
    {
        path: '/login',
        name: 'Login',
        meta:{name:'登录'},
        component: () => import('../views/Login.vue')
    },
    {
        path: '/register',
        name: 'Register',
        meta:{name:'注册'},
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
