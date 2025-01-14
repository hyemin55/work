import axios from 'axios';
import { GLOBAL_URL } from './util';

// 로그인한 사용자의 바구니를 가져온다.(CartView.vue 에서 작동)
export const fetchMemeberCart = async () => {
  try {
    const res = await axios.get(`${GLOBAL_URL}/cartProduct/select`, {
      headers: {
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      },
    });
    return res;
  } catch (error) {
    console.error(error);
  }
};

// 비회원 + 로그인 장바구니 합치기 (CartView.vue 에서 작동)
export const mergeMemberCart = async pushData => {
  try {
    await axios.post(`${GLOBAL_URL}/cart/merge`, pushData, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      },
    });
  } catch (error) {
    console.error(error);
  }
};

// DB 장바구니에 데이터 추가(장바구니 담기)
export const addCartDatabase = data => {
  try {
    const res = axios.post(`${GLOBAL_URL}/cart/add`, data, {
      headers: {
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      },
    });
    console.log(res);
  } catch (e) {
    console.log(e);
  }
};
