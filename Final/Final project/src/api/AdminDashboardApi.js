import axios from 'axios';
import { GLOBAL_URL } from './util';

// DashboardView.vue 오늘 작성된 리뷰
export const getDashboardReviewList = async () => {
  try {
    const dashboardReviewListRes = await axios.get(`${GLOBAL_URL}/admin/dashboard/review`, {
      });
    return dashboardReviewListRes.data;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// DashboardView.vue 오늘 판매 신청 리스트
export const getDashboardPendingList = async () => {
  try {
    const dashboardPendingListRes = await axios.get(`${GLOBAL_URL}/admin/dashboard/pending`, {
      });
    return dashboardPendingListRes.data;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// DashboardView.vue 오늘의 구매신청 리스트
export const getDashboardOrderList = async () => {
  try {
    const dashboardOrderListRes = await axios.get(`${GLOBAL_URL}/admin/dashboard/order`, {
      });
    return dashboardOrderListRes.data;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// DashboardView.vue 오늘의 로그인횟수
export const getDashboardLogInCount = async () => {
  try {
    const dashboardLogInCountRes = await axios.get(`${GLOBAL_URL}/admin/dashboard/member/logIn`, {
      });
    return dashboardLogInCountRes.data;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// DashboardView.vue 오늘의 회원가입 수
export const getDashboardJoinCount = async () => {
  try {
    const dashboardJoinCountRes = await axios.get(`${GLOBAL_URL}/admin/dashboard/member/join`, {
      });
    return dashboardJoinCountRes.data;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};
