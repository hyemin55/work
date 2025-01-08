<script setup>
import { postPassFormdata, postRejectFormdata } from '@/api/InspectionListApi';
import { GLOBAL_URL } from '@/api/util';
import { computed, ref, watch } from 'vue';

// 모달창 완료버튼(부모로 event 전달)
// 부모자에서 @sucess 이벤트를 발생시킴

const emit = defineEmits(['close', 'success']);
// const submitModal = () => {
//   emit('sucess');
// };
// 받아오는props의 정의 방법

const props = defineProps({
  Data: {
    type: Object,
    required: true,
  },
});

// 사용자가 수정한 아이템
const appraiserCustomData = props.Data?.DeliveryData || {};
const appraiserCustomDataView = appraiserCustomData.inspectionPassReqDto
  ? appraiserCustomData.inspectionPassReqDto
  : appraiserCustomData.inspectionRejectReqDto;
// 서버에서 받은 아이템
const serverItemReqBySeller = props.Data?.item || {};
const appraiserPreviewUrls = props.Data?.appraiserPreviewUrls || {};
console.log(serverItemReqBySeller);
console.log(appraiserPreviewUrls);

const TestResult = computed(() => {
  const testResult = serverItemReqBySeller?.TestResult;
  return testResult === 'Y' ? '합격' : '불합격';
});
// const appraiserFiles = ref([]);
// const previewUrls = ref([]);
// const imageLode = () => {
//   appraiserFiles.value = Array.from(appraiserCustomData.userImageFiles); // 선택된 파일 목록

//   if (appraiserFiles.value.length > 0) {
//     for (const file of appraiserFiles.value) {
//       if (file.type.startsWith('image/')) {
//         // 이미지 파일인 경우 URL 생성
//         previewUrls.value.push(URL.createObjectURL(file));
//       }
//     }
//   }
// };

const send = async () => {
  try {
    const formdata = new FormData();

    appraiserCustomData.userImageFiles.forEach(image => {
      formdata.append('userImageFiles', image);
    });
    if (appraiserCustomData.inspectionPassReqDto) {
      formdata.append(
        'inspectionPassReqDto',
        new Blob([JSON.stringify(appraiserCustomData.inspectionPassReqDto)], { type: 'application/json' }),
      );
      if (appraiserCustomData.passImageFiles.length > 0) {
        appraiserCustomData.passImageFiles.forEach(image => {
          formdata.append('passImageFiles', image);
        });
      } else {
        formdata.append('passImageFiles', new Blob([], { type: 'application/octet-stream' }));
      }
      for (let [key, value] of formdata.entries()) {
        console.log(key, value);
      }

      const passRes = await postPassFormdata(formdata);
      console.log(passRes.data);
      alert('합격 전송되었습니다.');
      emit('close', 'success');
    }
    if (appraiserCustomData.inspectionRejectReqDto) {
      formdata.append(
        'inspectionRejectReqDto',
        new Blob([JSON.stringify(appraiserCustomData.inspectionRejectReqDto)], { type: 'application/json' }),
      );

      if (appraiserCustomData.failImageFiles.length > 0) {
        appraiserCustomData.failImageFiles.forEach(image => {
          formdata.append('failImageFiles', image);
        });
      } else {
        formdata.append('failImageFiles', new Blob([], { type: 'application/octet-stream' }));
      }
      for (let [key, value] of formdata.entries()) {
        console.log(key, value);
      }
      const rejectRes = await postRejectFormdata(formdata);
      console.log(rejectRes.data);
      alert('불합격 전송되었습니다.');
      emit('close');
    }
    // 부모 컴포넌트에 모달 닫기 이벤트 전달
  } catch (error) {
    console.error('전송 중 오류가 발생했습니다:', error.response?.data || error.message);
    alert('전송 실패. 다시 시도해주세요.');
  }
};

const cancelModal = () => {
  emit('close');
};

watch(
  () => props.Data,
  newValue => {
    console.log('Data updated:', newValue);
  },
  { immediate: true },
);
</script>
<template>
  <section class="sale_modal">
    <article class="modal_page">
      <div class="Result_modal_page">
        <h1>
          최종 검수 결과 : <span>[ {{ TestResult }} ]</span>
        </h1>
        <h2 v-if="serverItemReqBySeller.PassGrade.gradeType">
          " {{ serverItemReqBySeller.PassGrade.gradeType }} " 등급입니다.
        </h2>
        <h3 v-if="serverItemReqBySeller.FailReason.rejectionReason">
          사유 : " {{ serverItemReqBySeller.FailReason.rejectionReason }} "
        </h3>
        <table class="Result_content">
          <thead>
            <tr>
              <th>구분</th>
              <th>내용</th>
              <th>단위</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th>No.</th>
              <td>{{ appraiserCustomDataView.pendingSaleId }}</td>
              <td>-</td>
            </tr>

            <tr>
              <th>카테고리</th>
              <td>{{ appraiserCustomDataView.inspectionCategoryReqDto.categoryName }}</td>
              <td>-</td>
            </tr>
            <tr>
              <th>브랜드명</th>
              <td>
                {{ appraiserCustomDataView.inspectionBrandReqDto.brandId }}.
                {{ appraiserCustomDataView.inspectionBrandReqDto.brandName }}
              </td>
              <td>-</td>
            </tr>
            <tr>
              <th>상품명</th>
              <td>{{ appraiserCustomDataView.inspectionProductReqDto.productName }}</td>
              <td>-</td>
            </tr>
            <tr v-if="serverItemReqBySeller.selectedProduct.size">
              <th>정량</th>
              <td>{{ serverItemReqBySeller.selectedProduct.size.toLocaleString() }}</td>
              <td>ml</td>
            </tr>
            <tr>
              <th>검수 후 실용량</th>
              <td>
                {{ appraiserCustomDataView.inspectionProductReqDto.productSize.toLocaleString() }}
              </td>
              <td>ml</td>
            </tr>
            <tr>
              <th>희망 판매 가격</th>
              <td>{{ Number(serverItemReqBySeller.expectedSellingPrice).toLocaleString() }}</td>
              <td>원</td>
            </tr>
            <tr>
              <th>권장 판매 가격</th>
              <td>
                {{ Number(appraiserCustomDataView.inspectionProductReqDto.verifiedSellingPrice).toLocaleString() }}
              </td>
              <td>원</td>
            </tr>
            <tr>
              <th>검수결과 참고사항</th>
              <td v-if="appraiserCustomDataView.inspectionContent">
                {{ appraiserCustomDataView.inspectionContent }}
              </td>
              <td v-else>-</td>
              <td>-</td>
            </tr>
            <tr>
              <th>등록하려는 사진</th>
              <td colspan="2" class="fileImagesBox">
                <p class="filenames" v-for="(image, index) in appraiserCustomData.userImageFiles" :key="index">
                  {{ index + 1 }}.
                  <img class="userSaleImage" :src="`${GLOBAL_URL}/api/file/download/${image.name}`" alt="" />
                </p>
                <p class="filenames" v-for="(image, index) in appraiserPreviewUrls" :key="index">
                  {{ appraiserCustomData.userImageFiles.length + index + 1 }}.
                  <img class="userSaleImage" :src="image" alt="" />
                </p>
                <!-- <p class="filenames" v-for="(imageName, index) in appraiserCustomData.passImageFiles" :key="index">
                  {{ index + appraiserCustomData.userImageFiles.length + 1 }}. {{ imageName.name }}
                </p> -->
                <!-- <img
                  class="userSaleImage"
                  v-for="(image, index) in appraiserCustomData.passImageFiles"
                  :key="index"
                  :src="`${image}`"
                  alt=""
                /> -->
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="Results_transfer_button">
        <button @click="send()">전송</button>
        <button @click="cancelModal()">취소</button>
      </div>
    </article>

    <article class="modal_background"></article>
  </section>
</template>

<style scoped>
/* 모달 전체 설정 ##################*/
.sale_modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100vh;
  z-index: 950;
  display: flex;
  align-items: center;
  justify-content: center;
}
/* 모달 설정 */
.modal_page {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
  /* 크기조절 여기서 */
  width: 600px;
  height: 800px;
  background-color: rgb(255, 255, 255);
  z-index: 11;
  border: 10px double forestgreen;
  padding: 5%;
  line-height: 2;
  font-size: 1.6rem;
  /* overflow-y: scroll; */
  /* scroll-behavior: smooth; */
}
/* 모달배경 설정 */
.modal_background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 10;
}
.Result_modal_page {
  text-align: center;
}
.Result_modal_page > h1 {
  font-size: 3rem;
}
.Result_modal_page > h1 > span {
  color: red;
}
.Result_content {
  width: 500px;
  margin: 3% 0;
  text-align: center;
  overflow-y: scroll;
}
th {
  background-color: var(--color-main-Lgray);
  width: 30%;
}
td {
  width: 55%;
}
table,
th,
td {
  border: 1px solid #333;
}
.filenames {
  text-align: left;
  padding: 0 2%;
}
.fileImagesBox {
  overflow-y: scroll;
  border: none;
  height: 130px;
  width: 127.5%;
  display: grid;
  align-items: end;
  grid-template-columns: repeat(2, 1fr);
  padding: 1%;
}

/* 스크롤바 전체 영역 */
.fileImagesBox::-webkit-scrollbar {
  width: 5px; /* 가로 스크롤일 경우 height */
}
/* 스크롤바 트랙 (배경) */
.fileImagesBox::-webkit-scrollbar-track {
  background: #f0f0f0;
}
/* 스크롤바 핸들 (움직이는 부분) */
.fileImagesBox::-webkit-scrollbar-thumb {
  background: #888;
  border-radius: 5px;
}
.userSaleImage {
  width: 100px;
  padding: 1%;
}
.Results_transfer_button {
  width: 70%;
  text-align: center;
}
.Results_transfer_button > button {
  font-size: 2rem;
  width: 30%;
  height: auto;
  padding: 0.5%;
  text-align: center;
  border-radius: 10px;
  margin: 0 8%;
  border: 2px solid var(--color-main-bloode);
}
.Results_transfer_button > button:hover {
  color: white;
  border: 2px solid var(--color-main-bloode);
  background-color: orange;
}
</style>
