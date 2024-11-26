import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import AboutView from '@/views/AboutView.vue'
import ComputedView from '@/views/ComputedView.vue'
import RefView from '@/views/RefReactiveView.vue'
import KeepAliveView from '@/views/KeepAliveView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component:AboutView
    },
    {
      path: '/computed',
      name: 'computed',
      component:ComputedView
    },
    {
      path: '/ref',
      name: 'ref',
      component:RefView
    },
    {
      path: '/keepAlive',
      name: 'keepAlive',
      component: KeepAliveView
    },
  ]
})

export default router
