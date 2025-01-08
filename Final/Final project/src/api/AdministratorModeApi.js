import axios from 'axios';
import { GLOBAL_URL } from './util';

// CategoryManagementView.vue
export const getCategoryManagementList = async (pageNum, size) => {
  try {
    const CategoryManagementListRes = await axios.get(`${GLOBAL_URL}/admin/product/management/category?`, {
      headers: {
        'Content-type': 'application/json',
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      },
      params: {
        pageNum: pageNum,
        size: size,
      },
    });
    return CategoryManagementListRes.data;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// ProductManagementView.vue
export const getProductManagementList = async (pageNum, size) => {
  try {
    const ProductManagementListRes = await axios.get(`${GLOBAL_URL}/admin/product/management/usedProduct?`, {
      headers: {
        'Content-type': 'application/json',
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      },
      params: {
        pageNum: pageNum,
        size: size,
      },
    });
    return ProductManagementListRes.data;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// UserManagementView.vue
export const getNotSortByRoleMemberData = async pageNumber => {
  try {
    const NotSortByRoleMemberDataRes = await axios.get(`${GLOBAL_URL}/admin/member/management`, {
      params: {
        pageNum: pageNumber,
      },
    });
    return NotSortByRoleMemberDataRes;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// UserManagementView.vue
export const getSortByRoleMemberData = async (sortByRole, pageNumber) => {
  try {
    const sortByRoleMemberDataRes = await axios.get(`${GLOBAL_URL}/admin/member/management`, {
      params: {
        role: sortByRole.value,
        pageNum: pageNumber.value,
      },
    });
    return sortByRoleMemberDataRes;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// UserManagementView.vue
export const postRoleChange = async (memberId, role) => {
  try {
    await axios.post(`${GLOBAL_URL}/admin/member/management/roleChange`, { memberId, role });
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// ReviewManagementView.vue
export const deleteReviewManagement = async reviewId => {
  try {
    await axios.get(`${GLOBAL_URL}/admin/review/management/delete?`, {
      headers: {
        'Content-type': 'application/json',
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      },
      params: {
        reviewId: reviewId,
      },
    });
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// ReviewManagementView.vue
export const getSearchKeyword = async (searchKeyword, pageNumber) => {
  try {
    const searchKeywordRes = await axios.get(`${GLOBAL_URL}/admin/review/management/search`, {
      params: {
        searchKeyword: searchKeyword,
        pageNum: pageNumber,
      },
    });
    return searchKeywordRes;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// ReviewManagementView.vue
export const notSortByReviewData = async (pageNumber, pageSize) => {
  try {
    const notSortByReviewDataRes = await axios.get(`${GLOBAL_URL}/admin/review/management`, {
      params: {
        pageNum: pageNumber,
        size: pageSize,
      },
    });
    return notSortByReviewDataRes;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// ReviewManagementView.vue
export const sortByReviewData = async (reviewSortBy, pageNumber, pageSize) => {
  try {
    const sortByReviewDataRes = await axios.get(`${GLOBAL_URL}/admin/review/management`, {
      params: {
        sort: reviewSortBy,
        pageNum: pageNumber,
        size: pageSize,
      },
    });
    return sortByReviewDataRes;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};
