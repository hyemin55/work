import CartView from '@/views/CartView.vue'
import LoginView from '@/views/login/LoginView.vue'
import MainView from '@/views/main/_MainView.vue'
import ProductDetailView from '@/views/product/productdetail/_ProductDetailView.vue'
import ProductListView from '@/views/product/ProductListView.vue'
import OauthView from '@/views/login/OauthView.vue'
import MypageView from '@/views/mypage/_MypageView.vue'
import { createRouter, createWebHistory } from 'vue-router'
import PaymentView from '@/views/payment/_PaymentView.vue'

const loginRouter = [
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
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    ...loginRouter,

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
      path: '/productsdetail/:idx',
      name: 'productsdetail',
      component: ProductDetailView,
    },
    // {
    //   path: '/productsdetail/:idx',
    //   name: 'productsdetail',
    //   component: () => import('@/views/ProductDetailView.vue'),
    // },

    {
      path: '/cart',
      name: 'cart',
      component: CartView,
    },
    {
      path: '/payment',
      name: 'payment',
      component: PaymentView,
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: MypageView,
    },
  ],
})

export default router
