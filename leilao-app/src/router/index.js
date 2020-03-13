import Vue from 'vue'
import VueRouter from 'vue-router'
import Unidades from '../views/unidade/Unidades.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Unidades
    },
    {
        path: '/unidades',
        name: 'Unidades',
        component: () => import(/* webpackChunkName: "about" */ '../views/unidade/Unidades.vue')
    },
    {
        path: '/empresas',
        name: 'Empresas',
        component: () => import(/* webpackChunkName: "about" */ '../views/empresa/Empresas.vue')
    },
    {
        path: '/leiloes',
        name: 'Leiloes',
        component: () => import(/* webpackChunkName: "about" */ '../views/leilao/Leiloes.vue')
    },
]

const router = new VueRouter({
    routes
})

export default router
