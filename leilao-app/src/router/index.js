import Vue from 'vue'
import VueRouter from 'vue-router'
import Unidades from '../views/unidade/Unidades';
import Empresas from '../views/empresa/Empresas';
import Empresa from '../views/empresa/Empresa';
import Leiloes from '../views/leilao/Leiloes';

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect: '/unidades'
    },
    {
        path: '/unidades',
        name: 'Unidades',
        component: Unidades
    },
    {
        path: '/empresas',
        name: 'Empresas',
        component: Empresas
    },
    {
        path: '/empresa/',
        component: Empresa,
    },
    {
        path: '/empresa/:id',
        component: Empresa,
        props: true
    },
    {
        path: '/leiloes',
        name: 'Leiloes',
        component: Leiloes
    },
]

const router = new VueRouter({routes})

export default router
