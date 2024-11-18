import axios from 'axios';
import { GLOBAL_URL } from './util';

export const fetchMemeberCart = async () => {
  try {
    // 로그인한 사용자의 바구니를 가져온다.
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

export const mergeMemberCart = async pushData => {
  try {
    // 비회원 + 로그인 장바구니 합치기
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
