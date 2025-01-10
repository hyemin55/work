<template>
  <table>
    <thead>
      <tr>
        <th>No.</th>
        <th>판매 신청자</th>
        <th>판매신청날짜</th>
        <th>배송현황</th>
        <th>배송도착일</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>{{ item.saleApplicationId }}</td>
        <td>{{ item.seller }}</td>
        <td>{{ item.saleApplicationDate }}</td>
        <td>{{ item.shipStatus }}</td>
        <td>{{ dateTimeFormat(item.shippedDate) }}</td>
      </tr>
    </tbody>
    <thead>
      <tr>
        <th>카테고리</th>
        <th colspan="2">브랜드</th>
        <th colspan="2">상품명</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td class="category">
          <p value="">기존값: {{ item.category }}</p>
          수정값:
          <select required v-model="item.categoryId">
            <option value="" selected disabled>선택</option>
            <option value="-" disabled>-------</option>
            <option value="0">Perfume</option>
            <option value="1">Diffuser</option>
            <option value="2">Candle</option>
          </select>
        </td>
        <!-- 브랜드 검색 -->
        <td rowspan="2">
          <input
            type="text"
            v-model="item.brandKeyword"
            placeholder="브랜드 검색"
            @input="fetchSuggestions('brand', item)"
          /><br />
          <select v-model="item.selectedBrand" @change="handleBrandChange(item)">
            <option
              :value="brand.brandId + '.' + brand.brandName"
              v-for="(brand, index) in item.brandSuggestions"
              :key="index"
              @select="fetchSuggestions('product', item)"
            >
              {{ brand.brandId }}.{{ brand.brandName }}
            </option>
            <option value="brandNameInput">직접입력</option>
          </select>
        </td>
        <td rowspan="2" class="InputDisplay">
          <p>기존값: {{ item.brand }}</p>
          <p v-if="item.selectedBrand != ''">
            수정값:
            <input
              v-if="item.selectedBrand === 'brandNameInput'"
              v-model="item.directInputBrand"
              type="text"
              placeholder="직접입력"
            />
            <span v-else>{{ item.selectedBrand }} </span>
          </p>
        </td>
        <!-- 상품명 검색 => 추후 브랜드 선택하면 select창이 뜨도록-->
        <td>
          <select v-model="item.selectedProduct" @change="handleProductChange(item)">
            <option :value="product" v-for="(product, index) in item.productSuggestions" :key="index">
              {{ product.productName }}ㆍ{{ product.size }} ml
            </option>
            <option value="productNameInput">직접입력</option>
          </select>
        </td>
        <td rowspan="2" class="InputDisplay">
          <p>기존값: {{ item.productName }}ㆍ{{ item.size }} ml</p>

          <p v-if="item.selectedProduct != ''">
            수정값:
            <input
              v-if="item.selectedProduct === 'productNameInput'"
              v-model="item.directInputProduct"
              type="text"
              placeholder="직접입력"
            />
            <span v-else>{{ item.selectedProduct.productName }}ㆍ{{ item.selectedProduct.size }} ml</span>
          </p>
        </td>
      </tr>
    </tbody>
    <thead>
      <tr>
        <th class="TestResult">
          용량 (ml)
          <span class="icon"
            ><img src="@/assets/img/icon/free-icon-font-exclamation-3917663.svg" alt="" />
            <div class="TestResultModal">
              <h3>용량 기준</h3>
              <div>
                판매 기준 용량은 상품 정량의 절반 이상이어야 합니다.
                <br />
                <br />
                <h4>● 합격 기준</h4>
                <span>
                  상품명 (프리지아 오드코롱) :
                  <br />
                  검수 용량 (25 ml) > 상품 정량 (50 ml)
                </span>
                <h4>● 불합격 기준</h4>
                <span>
                  상품명 (프리지아 오드코롱) :
                  <br />
                  검수 용량 (24 ml) > 상품 정량 (50 ml)
                </span>
              </div>
            </div>
          </span>
        </th>
        <th>희망판매가격</th>
        <th>권장판매가격</th>

        <th colspan="2">상세설명</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td rowspan="2" class="productSize">
          기존용량: {{ item.size }} ml
          <br />
          검수용량:
          <input
            type="number"
            v-model="item.inspectionSize"
            step="1"
            :min="item.selectedProduct.size / 2"
            :max="item.selectedProduct.size"
            placeholder="용량(ml)"
            required
            @blur="validatedInspectionSize(item)"
          />
        </td>
        <td>￦ {{ item.expectedSellingPrice.toLocaleString() }}</td>
        <td>
          <input
            type="number"
            v-model="item.inspectionSellingPrice"
            min="5000"
            step="10"
            placeholder="권장판매가격(5,000원 이상)"
          />
        </td>

        <td colspan="2">{{ item.userContent }}</td>
      </tr>
    </tbody>
    <thead>
      <tr>
        <th>사용 유무</th>
        <th>검수결과</th>
        <th v-if="item.TestResult == 'Y' || item.TestResult == ''" class="TestResult">
          등급
          <span class="icon"
            ><img src="@/assets/img/icon/free-icon-font-exclamation-3917663.svg" alt="" />
            <div class="TestResultModal">
              <h3>판매 등급</h3>
              <p>
                <b>A</b>: 새상품과 거의 동일한 상태
                <br />
                B: 새상품이나 박스가 훼손된 상태
                <br />
                C: 사용한 흔적이 있거나 상품에 흠집이나 스크래치가 있는 상태
                <br />
                D: 사용한 흔적이 많고 상품에 흠집이나 스크래치, 약간의 파손이 있는 상태
                <br />
                E: 사용한 흔적이 많고 상품에 흠집이나 스크래치, 파손, 훼손이 있지만 사용하기엔 문제가 없는 상태
              </p>
            </div></span
          >
        </th>
        <th v-if="item.TestResult == 'N'" class="TestResult">
          반려사유
          <span class="icon"
            ><img src="@/assets/img/icon/free-icon-font-exclamation-3917663.svg" alt="" />
            <div class="TestResultModal">
              <h3>판매 불가 사유</h3>
              <p>
                1. 파손 또는 훼손이 심해 판매할 수 없는 상태입니다.
                <br />
                2. 정품이 아닙니다.
                <br />
                3. 유통기한이 지났습니다.
                <br />
                4. 유통기한은 문제가 없으나 내용물 변질이 일어났습니다.
                <br />
                5. 판매 기준 용량 미달입니다.
                <br />
                6. 향수, 캔들, 디퓨저에 해당하지 않는 제품입니다.
                <br />
                7. 그 외(기타사항에 입력해주세요.)
              </p>
            </div>
          </span>
        </th>
        <th colspan="2">검수결과 참고사항</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>
          <select name="usedOrNot" id="" v-model="item.usedOrNot">
            <option value="false">새상품</option>
            <option value="true">중고상품</option>
          </select>
        </td>
        <td>
          <select name="TestResults" id="" v-model="item.TestResult" required>
            <option value="" selected disabled>선택</option>
            <option value="" disabled>-------</option>
            <option value="Y">합격</option>
            <option value="N">불합격</option>
          </select>
        </td>
        <td v-if="item.TestResult == 'Y' || item.TestResult == ''">
          <select name="PassGradeId" id="" v-model="item.PassGrade">
            <option value="" selected disabled>상품 등급 선택</option>
            <option value="" disabled>-------</option>
            <option :value="PassGrade" v-for="PassGrade in PassGradeList" :key="PassGrade">
              {{ PassGrade.gradeType }}. {{ PassGrade.gradeDescription }}
            </option>
          </select>
        </td>
        <td v-if="item.TestResult == 'N'">
          <select name="NotForSale" id="" v-model="item.FailReason">
            <option value="" selected disabled>판매불가 사유 선택</option>
            <option value="" disabled>-------</option>
            <option :value="FailReason" v-for="FailReason in FailReasonList" :key="FailReason">
              {{ FailReason.rejectionReasonId }}. {{ FailReason.rejectionReason }}
            </option>
          </select>
        </td>
        <td colspan="2">
          <textarea
            name=""
            v-model="item.Content"
            id=""
            maxlength="1000"
            placeholder="기타사항을 입력해주세요(최대 1000자)"
          ></textarea>
        </td>
      </tr>
    </tbody>
    <tbody>
      <tr>
        <th>상품 검색 대표사진</th>
        <th colspan="2">판매자 사진(등록할 사진을 선택해주세요.)</th>
        <th colspan="2">검수자 등록 사진</th>
      </tr>
      <tr>
        <td v-if="item.selectedProduct.mainImage">
          <img
            :src="`${GLOBAL_URL}/api/file/download/${item.selectedProduct.mainImage.filename}`"
            alt=""
            class="mainImage"
          />
        </td>
        <td v-else>
          <img src="@/assets/img/icon/free-icon-font-image-slash-10742150.svg" class="mainImage" alt="" />
          <p>이미지가 없습니다.</p>
        </td>
        <td colspan="2" rowspan="2">
          <div class="userSaleResImageList" v-for="(userSaleImage, index) in item.userSaleResImageList" :key="index">
            <div>
              <input
                :id="`${item.saleApplicationId}img-${index}`"
                type="checkbox"
                style="height: 2rem"
                @click="selectImages(userSaleImage, index)"
                v-model="item.userSaleResImageList[index].used"
              />
              <label :for="`${item.saleApplicationId}img-${index}`"
                ><img class="userSaleImage" :src="`${GLOBAL_URL}/api/file/download/${userSaleImage.name}`" alt=""
              /></label>
            </div>
          </div>
        </td>
        <td colspan="2">
          <input class="fileUpload" type="file" id="photo" multiple @change="handleFileUpload" />
          <template v-for="(url, index) in previewUrls" :key="index">
            <img :src="url" alt="미리보기 이미지" class="previewUrls" />
          </template>
        </td>
      </tr>
    </tbody>
  </table>
  <div>
    <button @click="send(item)">전송</button>
  </div>
  <article>
    <InspectionModalView v-if="InspectionModal" :Data="DeliveryData" @close="closeModal" />
  </article>
</template>

<script setup>
import { GLOBAL_URL } from '@/api/util';
import AnnouncementComponent from '@/components/admin/AnnouncementComponent.vue';
import axios from 'axios';
import { ref, watchEffect } from 'vue';
import InspectionModalView from '@/views/appraiser/InspectionModalView.vue';
import { dateTimeFormat } from '@/FormatData';
import { getBrandResponse, getFailReason, getPassGrade, getProductResponse } from '@/api/InspectionListApi';

const props = defineProps({
  item: {
    type: Object,
    required: true,
  },
});

const item = ref(props.item);
const PassGradeList = ref([]);
const FailReasonList = ref([]);
const categoriesList = ['Perfume', 'Diffuser', 'Candle'];
const DeliveryData = ref([]);
const InspectionModal = ref(false);
const appraiserFiles = ref([]);
const previewUrls = ref([]);
const userImageFiles = ref([]);
const emit = defineEmits(['dataUpdate']);

// 브랜드이름 직접 입력
const handleBrandChange = item => {
  // console.log(item);
  // '직접입력'을 선택했을 경우 빈 값을 초기화
  if (item.selectedBrand === 'brandNameInput') {
    item.directInputBrand = ''; // 입력값 초기화
  }
};

const handleProductChange = item => {
  if (item.selectedProduct === 'productNameInput') {
    item.directInputProduct = '';
  }
};

// 검수자 이미지파일 등록`
const handleFileUpload = event => {
  appraiserFiles.value = Array.from(event.target.files); // 선택된 파일 목록

  if (appraiserFiles.value.length > 0) {
    for (const file of appraiserFiles.value) {
      if (file.type.startsWith('image/')) {
        // 이미지 파일인 경우 URL 생성
        previewUrls.value.push(URL.createObjectURL(file));
      }
    }
  }

  // console.log('appraiserFiles.value', appraiserFiles.value);
};

// 판매자 사진 선택하기
const selectImages = (img, index) => {
  //isUsed : true = 사용하는 사진 , false =  사용하지 않는 사진
  img.used = !img.used;
  // console.log('선택한 이미지 번호', index, '선택한 이미지 내용', img);
};

// pageNation emit 업데이트

// InspectionMadal emit 업데이트
const closeModal = value => {
  InspectionModal.value = false;
  item.value.PassGrade = '';
  item.value.FailReason = '';
  userImageFiles.value = [];
  // for (let i = 0; item.value.userSaleResImageList.length > i; i++) {
  //   item.value.userSaleResImageList[i].used = false;
  // }
  item.value.userSaleResImageList.forEach(image => {
    image.used = false;
  });
  // console.log('value', value);
  if (value === 'success') {
    emit('dataUpdate');
  }
};

const validatedInspectionSize = item => {
  // console.log(item);
  if (item.selectedProduct === 'productNameInput') {
    return true;
  } else {
    if (item.inspectionSize > item.selectedProduct.size) {
      alert(
        '검수 용량이 상품 기준 용량보다 많습니다.' + '\n' + '상품 기준 용량 : ' + item.selectedProduct.size + ' ml',
      );
      return false;
    }
    if (item.inspectionSize < item.selectedProduct.size / 2) {
      alert(
        '검수 용량이 상품 기준 용량의 절반 이하입니다.' +
          '\n' +
          '상품 기준 용량 : ' +
          item.selectedProduct.size +
          ' ml',
      );
      return false;
    } else {
      return true;
    }
  }
};

// ======================검수 전송 버튼 눌렀을 때======================
const send = async item => {
  // console.log('item', item);
  // console.log('item.userSaleResImageList', item.userSaleResImageList);
  const InspectionResultId = [item.PassGrade.gradeId, item.FailReason.rejectionReasonId];

  for (let i = 0; item.userSaleResImageList.length > i; i++) {
    if (item.userSaleResImageList[i].used && item.userSaleResImageList[i] !== userImageFiles.value) {
      userImageFiles.value.push(item.userSaleResImageList[i]);
    }
  }
  // console.log(userImageFiles.value);

  // 각 항목에 필드 이름과 값을 함께 저장
  const valueError = [
    { field: '검수 결과', value: item.TestResult },
    { field: '판매 신청자', value: item.saleApplicationId },
    { field: '카테고리1', value: item.categoryId + 1 },
    { field: '카테고리2', value: categoriesList[item.categoryId] },
    { field: '브랜드', value: item.selectedBrand.split('.')[0] },
    { field: '브랜드', value: item.selectedBrand.split('.')[1] },
    { field: '상품명', value: item.selectedProduct.productName },
    { field: '용량', value: item.inspectionSize },
    { field: '권장 판매 가격', value: item.inspectionSellingPrice },
    {
      field: item.TestResult === 'Y' ? '등급' : '반려사유',
      value: item.TestResult === 'Y' ? InspectionResultId[0] : InspectionResultId[1],
    },
  ];

  // 배열 순회하며 값 검증
  for (let i = 0; i < valueError.length; i++) {
    // console.log('valueError.values', valueError[i]);

    if (valueError[i].value === '' || valueError[i].value === null || valueError[i].value === 0) {
      alert(`"${valueError[i].field}" 값이 입력되지 않았습니다.`);
      return; // 입력 오류 발생 시 함수 종료
    }
  }

  const error = validatedInspectionSize(item);
  if (!error) return;

  // console.log(appraiserFiles.value);
  if (item.TestResult === 'Y') {
    // console.log('모든 값이 올바르게 입력되었습니다.');

    const passData = {
      inspectionPassReqDto: {
        pendingSaleId: item.saleApplicationId,
        gradeId: item.PassGrade.gradeId,
        inspectionCategoryReqDto: {
          categoryId: item.categoryId,
          categoryName: categoriesList[item.categoryId],
        },
        inspectionBrandReqDto: {
          brandId: item.selectedBrand === 'brandNameInput' ? null : item.selectedBrand.split('.')[0],
          brandName: item.selectedBrand === 'brandNameInput' ? item.directInputBrand : item.selectedBrand.split('.')[1],
        },
        inspectionProductReqDto: {
          productName:
            item.selectedProduct === 'productNameInput' ? item.directInputProduct : item.selectedProduct.productName,
          productId: item.selectedProduct === 'productNameInput' ? null : item.selectedProduct.productId,
          productSize: item.inspectionSize,
          verifiedSellingPrice: item.inspectionSellingPrice,
          quantity: 0,
        },
        userSaleReqImageDtos: userImageFiles.value,
        passSaleReqImageDtos: appraiserFiles.value.map((passFile, index) => ({
          name: passFile.name,
          desc: `image-${index + 1}`,
        })),
        // false면 새상품, true면 중고상품
        usedOrNot: item.usedOrNot,
        inspectionContent: item.Content,
        inspectionResult: true,
      },
      passImageFiles: appraiserFiles.value,
      userImageFiles: userImageFiles.value,
    };

    DeliveryData.value = {
      DeliveryData: passData,
      item: item,
      appraiserPreviewUrls: previewUrls.value,
    };
  } else if (item.TestResult === 'N') {
    const failData = {
      inspectionRejectReqDto: {
        pendingSaleId: item.saleApplicationId,
        rejectionReasonId: item.FailReason.rejectionReasonId,
        inspectionCategoryReqDto: {
          categoryId: item.categoryId,
          categoryName: categoriesList[item.categoryId],
        },
        inspectionBrandReqDto: {
          brandId: item.selectedBrand.split('.')[0],
          brandName: item.selectedBrand.split('.')[1],
        },
        inspectionProductReqDto: {
          productName: item.selectedProduct.productName,
          productId: item.selectedProduct.productId,
          productSize: item.inspectionSize,
          verifiedSellingPrice: item.inspectionSellingPrice,
          quantity: 0,
        },
        userSaleReqImageDtos: userImageFiles.value,
        failSaleReqImageDtos: appraiserFiles.value.map((passFile, index) => ({
          name: passFile.name,
          desc: `image-${index + 1}`,
        })),
        inspectionContent: item.Content,
        inspectionResult: false,
      },
      failImageFiles: appraiserFiles.value,
      userImageFiles: userImageFiles.value,
    };
    // console.log('failData', failData);
    DeliveryData.value = { DeliveryData: failData, item: item, appraiserPreviewUrls: previewUrls.value };
  }
  InspectionModal.value = true;
  // console.log(DeliveryData.value);
  // console.log('모달창 나옵니다.');
};

// 브랜드, 상품명 검색 입력 시 호출
const fetchSuggestions = async (type, item) => {
  if (item.brandKeyword.length < 2) return;

  // 검색어가 변경될 때만 API를 호출하는 명령어
  // clearTimeout(debounceTimeout);
  if (type === 'brand' && item.brandKeyword.length > 1) {
    // 최소 2자 입력 후 검색
    try {
      // 브랜드 검색어 입력 시 서버와 통신
      const brandResponseRes = await getBrandResponse(item.brandKeyword);
      item.brandSuggestions = brandResponseRes.data;
      if (item.brandSuggestions.length > 0) {
        item.selectedBrand = `${item.brandSuggestions[0].brandId}.${item.brandSuggestions[0].brandName}`;
      } else {
        item.selectedBrand = '';
      }
    } catch (error) {
      console.error('Error fetching brandSuggestions:', error);
    }
  }

  if (Number(item.selectedBrand.split('.')[0]) != 0 || Number(item.selectedBrand.split('.')[0]) != null) {
    try {
      // 상품명 검색어 입력 시 서버와 통신
      const productResponseRes = await getProductResponse(Number(item.selectedBrand.split('.')[0]));
      item.productSuggestions = productResponseRes.data;
      if (item.productSuggestions.length > 0) {
        // console.log(item.productSuggestions);
        item.selectedProduct = item.productSuggestions[0];
      } else {
        item.selectedProduct = '';
      }
    } catch (error) {
      console.error('Error fetching productSuggestions:', error);
    }
  } else {
    return;
  }
};

watchEffect(async () => {
  // 검수 합격 등급 리스트
  const passRes = await getPassGrade();
  PassGradeList.value = passRes.data;
  // 검수 불합격 사유 리스트
  const failRes = await getFailReason();
  FailReasonList.value = failRes.data;
});
</script>

<style scoped>
table {
  width: 100%;
  font-size: 1.4rem;
  text-align: center;
}

th {
  width: 15%;
  padding: 10px;
  background-color: var(--color-main-Lgray);
  /* background-color: #a7b4a8 ; */
  /* border-bottom: 0.5px solid #e5e5e5; */
  border-right: 2px solid white;
}
th:last-child {
  border-right: none;
}
td {
  width: 15%;
  text-align: center;
  padding: 20px 10px;
  margin: 5px 0;
  border-right: 2px solid var(--color-main-Lgray);
}
td:last-child {
  border-right: none;
}

textarea {
  resize: none;
  height: auto;
  width: 100%;
  border: 0.5px solid var(--color-text-gray);
}
input,
select,
option {
  width: 70%;
  border-radius: 5px;
  border: 0.5px solid var(--color-text-gray);
}
input:focus,
select:focus,
option:focus,
textarea:focus {
  outline: none;
  border-color: var(--color-main-bloode);
  background-color: var(--color-main-pink);
}
button {
  padding: 10px;
  background-color: #8f9d8d;
  box-shadow: inset 2px 2px 4px rgb(255, 255, 255);
  border-radius: 9px;
  width: 100%;
  text-align: center;
  font-size: 1.8rem;
  color: white;
  transition: all 0.3s ease;
}
button:hover {
  background-color: #627c85;
  box-shadow: inset 2px 2px 4px rgba(0, 0, 0, 0.2);
  transform: translateY(2px);
}
.InputDisplay,
.category,
.productSize {
  text-align: left;
}
.icon {
  position: relative;
  justify-content: center;
  align-content: center;
}
.icon:hover .TestResultModal {
  display: block;
}
.icon > img {
  width: 1.4rem;
}
.mainImage{
  width: 150px;
}
.userSaleImage,
.previewUrls {
  width: 100px;
  height: auto;
}
.userSaleResImageList {
  float: left;
  overflow: hidden;
  line-height: 2;
  padding: 0.5%;
}
.userSaleImage {
  cursor: pointer;
  margin: 0 5px;
  border: 0.5px solid #ccc;
  border-radius: 8px;
}
.fileUpload {
  width: 100%;
  padding: 1%;
  margin-bottom: 10px;
  border: 0.5px solid var(--color-text-gray);
  border-radius: 5px;
}
.previewUrls {
  border: 0.5px solid #ccc;
  border-radius: 8px;
  padding: 1%;
}
.TestResultModal {
  width: 250px;
  height: 265px;
  padding: 15px;
  text-align: left;
  line-height: 1.5;
  font-family: 'Pretendard-Light';
  background-color: var(--color-main-pink);
  position: absolute;
  top: -285px;
  left: -118px;
  display: none;
  border-radius: 15px; /* 둥근 모서리 */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
}

.TestResultModal::after {
  content: '';
  position: absolute;
  bottom: -20px; /* 삼각형 위치 조정 */
  left: 50%; /* 가운데 정렬 */
  transform: translateX(-50%);
  width: 0;
  height: 0;
  border-left: 15px solid transparent;
  border-right: 15px solid transparent;
  border-top: 20px solid var(--color-main-Lgray); /* 말풍선 삼각형 색상 */
}
</style>
