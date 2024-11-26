import CartView from '@/views/user/CartView.vue';
import LoginView from '@/views/user/login/LoginView.vue';
import MainView from '@/views/user/main/_MainView.vue';
import ProductDetailView from '@/views/user/product/productdetail/_ProductDetailView.vue';
import ProductListView from '@/views/user/product/ProductListView.vue';
import OauthView from '@/views/user/login/OauthView.vue';
import MypageView from '@/views/user/mypage/_MypageView.vue';
import { createRouter, createWebHistory } from 'vue-router';
import PaymentView from '@/views/user/payment/_PaymentView.vue';
import NotFoundPage from '@/views/loding/NotFoundPage.vue';
import { useUserStore } from '@/stores/Login';
import AnnouncementView from '@/views/admin/announcementVIew.vue';
import OrderManagementView from '@/views/admin/OrderManagementView.vue';
import ProductManagementView from '@/views/admin/ProductManagementView.vue';
import ReviewManagementView from '@/views/admin/ReviewManagementView.vue';
import StatisticsView from '@/views/admin/StatisticsView.vue';
import UserManagementView from '@/views/admin/UserManagementView.vue';
import _MainDashboardView from '@/views/admin/_MainDashboardView.vue';

const loginRouters = [
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
];

const adminRouters = [
  {
    path: '/',
    meta: { nickName: '민이♡' }, // 공통 meta
    children: [
      {
        path: '/mainDashboard',
        name: 'mainDashboard',
        component: _MainDashboardView,
      },
      {
        path: 'announcement',
        name: 'announcement',
        component: AnnouncementView,
      },
      {
        path: 'orderManagement',
        name: 'orderManagement',
        component: OrderManagementView,
      },
      {
        path: 'productManagement',
        name: 'productManagement',
        component: ProductManagementView,
      },
      {
        path: 'reviewManagement',
        name: 'reviewManagement',
        component: ReviewManagementView,
      },
      {
        path: 'statistics',
        name: 'statistics',
        component: StatisticsView,
      },
      {
        path: 'userManagement',
        name: 'userManagement',
        component: UserManagementView,
      },
    ],
  },
];

const routers = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    ...loginRouters,
    ...adminRouters,
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
    // {
    //   path: '/productsdetail/:idx',
    //   name: 'productsdetail',
    //   component: ProductDetailView,
    // },
    {
      path: '/productsdetail/:idx',
      name: 'productsdetail',
      component: () => import('@/views/user/product/productdetail/_ProductDetailView.vue'),
    },

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
    {
      path: '/:catchAll(.*)', // catch-all 경로를 정규 표현식으로 설정
      component: NotFoundPage,
    },
  ],
  scrollBehavior(to, from, savedPosition) {
    // 뒤로가기나 새로고침이 아닌 경우(savedPosition이 있으면 해당 위치로 복원)
    if (savedPosition) {
      return savedPosition;
    }
    // 모든 라우트 이동 시 스크롤을 맨 위로 이동
    return { top: 0 };
  },
});
// routers.beforeEach((to, from, next) => {
//   const userStore = useUserStore();
//   const userRole = userStore.nickName;
//   if (to.meta.nickName && to.meta.nickName !== '민이♡') {
//     return next('/main');
//   }
//   next();
// });
export default routers;
