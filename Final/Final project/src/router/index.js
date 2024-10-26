import CartView from '@/views/CartView.vue'
import LoginView from '@/views/LoginView.vue'
import MainView from '@/views/MainView.vue'
import ProductDetailView from '@/views/ProductDetailView.vue'
import ProductListView from '@/views/ProductListView.vue'
import OauthView from '@/views/OauthView.vue'
import MypageView from '@/views/mypage/MypageView.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: MainView,
    },
    {
      path: '/category/:title/:idx', // 매개변수 추가
      name: 'productList',
      component: ProductListView,
    },
    {
      path: '/search',
      name: 'search',
      component: ProductListView,
    },
    {
      path: '/cart',
      name: 'cart',
      component: CartView,
    },
    {
      path: '/login2',
      name: 'login2',
      component: LoginView,
    },
    {
      path: '/login',
      name: 'login',
      component: OauthView,
    },
    {
      path: '/productsdetail/:idx',
      name: 'productsdetail',
      component: ProductDetailView,
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: MypageView,
    },
  ],
})

export default router
