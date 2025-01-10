import axios from 'axios';
import { GLOBAL_URL } from './util';

export const getBestProducts = async () => {
  try {
    const bestRes = await axios.get(`${GLOBAL_URL}/api/products/best`);
    // console.log(bestRes.data);
    return bestRes.data;
  } catch (err) {
    console.log(err);
    return err;
  }
};

export const getEditorPicks = async () => {
  try {
    const editorPicksRes = await axios.get(`${GLOBAL_URL}/mainPage/editorPick`);
    // console.log(editorPicksRes.data);
    return editorPicksRes.data;
  } catch (err) {
    console.log(err);
    return err;
  }
};

export const getMainList = async (pageNum, size) => {
  try {
    const getMainListRes = await axios.get(`${GLOBAL_URL}/api/products/random?pageNum=${pageNum}&size=${size}`);
    // console.log(getMainListRes.data);
    return getMainListRes.data;
  } catch (err) {
    console.log(err);
    return err;
  }
};

export const getNewSildes = async () => {
  try {
    const newSildesRes = await axios.get(`${GLOBAL_URL}/api/products/new`);
    // console.log(newSildesRes.data);
    return newSildesRes.data;
  } catch (err) {
    console.log(err);
    return err;
  }
};
