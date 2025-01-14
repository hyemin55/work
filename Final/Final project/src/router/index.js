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
import StatisticsView from '@/views/admin/statistics/StatisticsView.vue';
import UserManagementView from '@/views/admin/UserManagementView.vue';
import MainDashboardView from '@/views/admin/_MainDashboardView.vue';
import { loginCheck } from '@/api/KakaoLoginApi';
import MainInspectionListView from '@/views/appraiser/_MainInspectionListView.vue';
import ApprovedListView from '@/views/appraiser/ApprovedListView.vue';
import PetListView from '@/views/appraiser/PetListView.vue';
import ProductMasonryView from '@/views/user/product/ProductMasonryView.vue';
import AnnouncementView from '@/views/admin/AnnouncementVIew.vue';
import CategoryManagementView from '@/views/admin/CategoryManagementView.vue';
import OrderCompleteDetale from '@/components/OrderCompleteDetale.vue';

// 로그인
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

// 관리자 페이지
const adminRouters = [
  {
    path: '/',
    meta: { role: 'ADMIN' }, // 공통 meta
    children: [
      {
        path: 'mainDashboard',
        name: 'mainDashboard',
        component: MainDashboardView,
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
        path: 'categoryManagement',
        name: 'categoryManagement',
        component: CategoryManagementView,
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

// 검수자 및 관리자 페이지
const appraiserRouters = [
  {
    path: '/',
    meta: { role: ['APPRAISER', 'ADMIN'] },
    // 공통 meta
    children: [
      {
        path: 'mainInspectionList',
        name: 'mainInspectionList',
        component: MainInspectionListView,
      },
      {
        path: 'approvedList',
        name: 'approvedList',
        component: ApprovedListView,
      },
      {
        path: 'petList',
        name: 'petList',
        component: PetListView,
      },
      {
        path: 'announcement',
        name: 'announcement',
        component: AnnouncementView,
      },
    ],
  },
];
const userRouters = [
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
    path: '/masonry/:idx',
    name: 'masonry',
    component: ProductMasonryView,
  },
  {
    path: '/productsdetail/:idx',
    name: 'productsdetail',
    component: () => import('@/views/user/product/productdetail/_ProductDetailView.vue'),
  },
  // {
  //   path: '/productsdetail/:idx#section',
  //   name: 'productsdetail',
  //   component: () => import('@/views/user/product/productdetail/_ProductDetailView.vue'),
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
  {
    path: '/ordercomplete',
    name: 'ordercomplete',
    component: OrderCompleteDetale,
  },
];
const routers = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    ...loginRouters,
    ...adminRouters,
    ...appraiserRouters,
    ...userRouters,
    {
      path: '/',
      name: 'main',
      component: MainView,
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
  if (to.meta.role) {
    // 관리자페이지들어가면 무조건 작동
    if (sessionStorage.getItem('token')) {
      if (useStore.role === '') {
        res = await loginCheck();
        useStore.login(res.data); //스토어 등록
      }
      const userRole = useStore.role;
      const roles = Array.isArray(to.meta.role) ? to.meta.role : [to.meta.role];
      if (!roles.includes(userRole)) {
        console.log('index 경로이동실패', useStore.role);
        alert('페이지 권한이 없습니다.');
        return next('/');
      }
      useStore.moveNotMain();
      return next(); // 권한이 유효하면 계속 진행
    } else {
      alert('로그인이 필요한 페이지입니다.');
      return next('/login2');
    }
  } else {
    if (sessionStorage.getItem('token')) {
      res = await loginCheck();
      useStore.login(res.data); //스토어 등록
      useStore.moveMain();
      return next();
    } else {
      useStore.moveMain();
      // console.log('next로 이동', useStore.role);
      next();
    }
  }
});
export default routers;
