import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import MonthView from '@/views/MonthView.vue';
import AboutView from '@/views/AboutView.vue';
import testview from '@/views/test.vue';
import LoginView from '../views/Login.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/month',
      name: 'month',
      component: MonthView,
    },
    {
      path: '/about',
      name: 'about',
      component: AboutView,
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView,
    },
    {
      path: '/test',
      name: 'test',
      component: testview,
    },
  ],
});

export default router;
