import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import MonthView from '@/views/MonthView.vue';
import AboutView from '@/views/AboutView.vue';
import testview from '@/views/test.vue';
import LoginView from '@/views/login/LoginView.vue';
import OauthView from '@/views/login/OauthView.vue';

const loginRouter = [
  {
    path: '/login',
    name: 'login',
    component: LoginView,
  },
  {
    path: '/oauth',
    name: 'oauth',
    component: OauthView,
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    // 위의 const를 그대로 넣고싶으면 ... 변수명을 입력해준다. 자바스크립스트에서 새로나온 문법.
    ...loginRouter,

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
      path: '/test',
      name: 'test',
      component: testview,
    },
  ],
});

export default router;
