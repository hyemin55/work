import axios from 'axios';
import { GLOBAL_URL } from './util';

// _MainInspectionListView.vue
export const getInspectionListCount = async () => {
  try {
    const inspectionListCountRes = await axios.get(`${GLOBAL_URL}/api/inspection/pending-sale/total-count`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      },
    });
    return inspectionListCountRes;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// _MainInspectionListView.vue
export const getInspectionList = async (pageNumber, pageSize) => {
  try {
    const inspectionListRes = await axios.get(`${GLOBAL_URL}/api/inspection/list`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      },
      params: {
        pageNum: pageNumber,
        size: pageSize,
      },
    });
    return inspectionListRes;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// MainInspectionItemView.vue
export const getPassGrade = async () => {
  try {
    const passGradeRes = await axios.get(`${GLOBAL_URL}/api/inspection/pass/grade`);
    return passGradeRes;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// MainInspectionItemView.vue
export const getFailReason = async () => {
  try {
    const failReasonRes = await axios.get(`${GLOBAL_URL}/api/inspection/fail/reason`);
    return failReasonRes;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// MainInspectionItemView.vue
export const getBrandResponse = async brandKeyword => {
  try {
    const brandResponseRes = await axios.get(`${GLOBAL_URL}/api/inspection/search-brands`, {
      params: { keyword: brandKeyword },
    });
    return brandResponseRes;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// MainInspectionItemView.vue
export const getProductResponse = async brandId => {
  try {
    const productResponseRes = await axios.get(`${GLOBAL_URL}/api/inspection/search-products`, {
      params: { brandId: brandId },
    });
    return productResponseRes;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// InspectionModalView.vue
export const postPassFormdata = async formdata => {
  try {
    const passFormdataRes = await axios.post(`${GLOBAL_URL}/api/inspection/pass`, formdata, {
      headers: {
        'Content-Type': 'multipart/form-data',
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      },
    });
    return passFormdataRes;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// InspectionModalView.vue
export const postRejectFormdata = async formdata => {
  try {
    const rejectFormdataRes = await axios.post(`${GLOBAL_URL}/api/inspection/reject`, formdata, {
      headers: {
        'Content-Type': 'multipart/form-data',
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      },
    });
    return rejectFormdataRes;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// ApprovedListView.vue
export const getAcceptedList = async () => {
  try {
    const acceptedListRes = await axios.get(`${GLOBAL_URL}/api/verifiedSale/accepted-list?`, {
      headers: {
        'Content-type': 'application/json',
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      },
      params: {
        pageNum: 0,
        size: 5,
      },
    });
    return acceptedListRes.data;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// PetListView.vue
export const getRejectionList = async () => {
  try {
    const rejectionListRes = await axios.get(`${GLOBAL_URL}/api/verifiedSale/rejection-list?`, {
      headers: {
        'Content-type': 'application/json',
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      },
      params: {
        pageNum: 0,
        size: 5,
      },
    });
    return rejectionListRes.data;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};
