import axios from 'axios';
import { GLOBAL_URL } from '@/api/util';

export const login = async code => {
  try {
    const res = await axios.get(`${GLOBAL_URL}/kakao/login?code=${code}`);
    if (res.status.toString().startsWith('2')) {
      sessionStorage.setItem('token', res.data);
      console.log('token', res.data);
    }
    console.log(res.data);
    return res;
  } catch (err) {
    console.log(err);
    return err;
  }
};

export const loginCheck = async () => {
  try {
    const res = await axios.get(`${GLOBAL_URL}/member/info`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      },
    });
    console.log(res.data);
    return res;
  } catch (err) {
    console.error(err);
    return err;
  }
};

export const logout = async ACCESS_TOKEN => {
  const res = await axios.post('https://kapi.kakao.com/v1/user/logout', {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
      Authorization: `Bearer ${ACCESS_TOKEN}`,
    },
  });
  console.log(res);
};
