import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import hi from '../views/hi.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/hi',
      name: 'hi',
      component: hi
    }
  ]
})

export default router
