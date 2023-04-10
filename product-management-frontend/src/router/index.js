import { createRouter, createWebHistory } from 'vue-router'
import Home from '../components/Home'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            component: Home
        },
        {
            path: '/categories',
            component: () => import('../components/ListCategories.vue')
        },
        {
            path: '/addCategory',
            component: () => import('../components/AddCategory.vue')
        },
        {
            path: '/updateCategory/:id',
            component: () => import('../components/UpdateCategory.vue')
        },
        {
            path: '/updateProduct/:id',
            component: () => import('../components/UpdateProduct.vue')
        },
        {
            path: '/viewCategory/:id',
            component: () => import('../components/ViewCategory.vue')
        },
        {
            path: '/viewProduct/:id',
            component: () => import('../components/ViewProduct.vue')
        },
        {
            path: '/products',
            component: () => import('../components/ListProducts.vue')
        },{
            path: '/addProduct',
            component: () => import('../components/AddProduct.vue')
        }
    ],
})

export default router