import axios from 'axios';
import { GLOBAL_URL } from '@/api/util';

// ReviewComponent 처음 렌더링 시 사용할 상품리스트 정보 Api
// export const getReviewsData = async idx => {
//   try {
//     const reviewsData = await axios.get(`${GLOBAL_URL}/detail/review/${idx}`);
//     console.log('getReviewsData = ', reviewsData);
//     return reviewsData;
//   } catch (error) {
//     console.log('getReviewsData : ', error);
//     throw error;
//   }
// };

// ReviewComponent 주소줄의 idx가 바뀔때 리뷰내용, 페이지네이션 변경할 데이터 Api
export const getViewCurrentPage = async (idx, currentPage) => {
  try {
    const reviewsData = await axios.get(`${GLOBAL_URL}/detail/review/${idx}?pageNum=${currentPage}`);
    // console.log('getViewCurrentPage = ', reviewsData);
    return reviewsData;
  } catch (error) {
    console.log('getViewCurrentPage : ', error);
    return null;
  }
};

// ProductDetailReviewSlideView
export const getReviewImageList = async idx => {
  try {
    const reviewImageList = await axios.get(`${GLOBAL_URL}/detail/reviewImageList/${idx}`);
    // console.log('getReviewImageList = ', reviewImageList);
    return reviewImageList;
  } catch (error) {
    console.log('getReviewImageList', error);
    throw error;
  }
};

// ProductDetailReviewView
export const getstarCounting = async idx => {
  try {
    const starCountingData = await axios.get(`${GLOBAL_URL}/detail/detailReviewInfo/${idx}`);
    // console.log('getstarCounting = ', starCountingData);
    return starCountingData;
  } catch (error) {
    console.log('getstarCounting', error);
    throw error;
  }
};

//ProductDetailInfoSectionView
export const getProductData = async idx => {
  try {
    const productData = await axios.get(`${GLOBAL_URL}/detail/detailProductInfo/${idx}`);
    // console.log('getProductData = ', productData);
    return productData.data;
  } catch (error) {
    if (error.response && error.response.status === 500) {
      const NotFound = error.response && error.response.status === 500;
      console.error('상품을 찾을 수 없습니다.');
      alert('해당 상품을 찾을 수 없습니다.'); // 404 오류일 때
      // 필요에 따라 사용자에게 알림을 띄우거나, 기본 데이터를 반환할 수도 있습니다.
      return NotFound;
    } else {
      console.error('오류가 발생했습니다:', error.message); // 다른 오류 처리
    }
    throw error;
  }
};

// ProductDetailInfoSectionView
export const getReviewData = async idx => {
  try {
    const reviewData = await axios.get(`${GLOBAL_URL}/detail/detailReviewInfo/${idx}`);
    // console.log('getReviewData = ', reviewData);
    return reviewData.data;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

// ProductDetailSlideView
export const getSlideImages = async idx => {
  try {
    const slideImagesData = await axios.get(`${GLOBAL_URL}/detail/images/${idx}`);
    // console.log('getSlideImages = ', slideImagesData);
    return slideImagesData;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

//ReviewComponent
export const getReviewList = async (idx, currentPage) => {
  try {
    const reviewListRes = await axios.get(`${GLOBAL_URL}/detail/favorite/${idx}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      },
      params: {
        pageNum: currentPage,
      },
    });
    // console.log('getSlideImages = ', slideImagesData);
    return reviewListRes;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};

//ReviewComponent
export const getReviewListGoodIconState = async reviewId => {
  try {
    const ReviewListGoodIconStateRes = await axios.get(
      `${GLOBAL_URL}/detail/favorite/clickFavorite?reviewId=${reviewId}`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${sessionStorage.getItem('token')}`,
        },
      },
    );
    // console.log('getSlideImages = ', slideImagesData);
    return ReviewListGoodIconStateRes;
  } catch (error) {
    console.log('', error);
    throw error;
  }
};
