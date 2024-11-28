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
import OrderManagementView from '@/views/admin/OrderManagementView.vue';
import ProductManagementView from '@/views/admin/ProductManagementView.vue';
import ReviewManagementView from '@/views/admin/ReviewManagementView.vue';
import StatisticsView from '@/views/admin/StatisticsView.vue';
import UserManagementView from '@/views/admin/UserManagementView.vue';
import _MainDashboardView from '@/views/admin/_MainDashboardView.vue';
import AnnouncementView from '@/views/admin/AnnouncementView.vue';
import { loginCheck } from '@/api/KakaoLoginApi';

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
        path: 'mainDashboard',
        name: 'mainDashboard',
        meta: { role: 'admin' },
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

routers.beforeEach(async (to, from, next) => {
  const useStore = useUserStore();
  let res = [null];
  if (to.meta.nickName) {
    // 관리자페이지들어가면 무조건 작동

    if (sessionStorage.getItem('token')) {
      res = await loginCheck();
      useStore.login(res.data); //스토어 등록
      const userRole = useStore.nickName;
      if (to.meta.nickName === '민이♡' && userRole !== '민이♡') {
        console.log('index 경로이동실패', useStore.nickName);
        alert('관리자 권한이 없습니다.');
        return next('/');
      } else if (to.meta.nickName === '민이♡' && userRole === '민이♡') {
        // alert('관리자 페이지로 이동합니다.');
        return next();
      }
    }
    alert('로그인이 필요한 페이지입니다.');
    return next('/login2');
  } else if (useStore.loginCheck) {
    // useStore.login(); //스토어 등록
    return next();
  }
  next();
  console.log('next로 이동', useStore.nickName);
});
export default routers;
