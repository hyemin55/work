import axios from 'axios';

const obj = {
  baseURL: 'http://localhost:20000',
  headers: { 'content-Type': 'application/json' },
};

const apiClient = axios.create(obj);

const url = 'http://localhost:20000';
export const login = async code => {
  try {
    const res = await axios.get(`${url}/kakao/login?code=${code}`);
    return res.data;
  } catch (err) {
    console.log(err);
    return err;
  }
};

export const msgSend = async message => {
  console.log('token ' + localStorage.getItem('token'));
  try {
    const res = await axios.get(`${url}/kakao/msg?message=${message}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      },
    });
    return res;
  } catch (err) {
    console.error(err);
    return err;
  }
};

export const loginCheck = async () => {
  try {
    const res = await axios.get(`${url}/user/info`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      },
    });
    return res;
  } catch (err) {
    console.error(err);
    return err;
  }
};
