<script setup>
import { GLOBAL_IMP_KEY, GLOBAL_URL } from '@/api/util'
import { usePayMentStore } from '@/stores/PayMentStore'
import PayMethod from '@/views/payment/PayMethodView.vue'
import PayMoney from '@/views/payment/PayMoneyView.vue'
import PayProduct from '@/views/payment/PayProductView.vue'
import PayUserInfo from '@/views/payment/PayUserInfoView.vue'
import axios from 'axios'
import { computed, onMounted, ref, watchEffect } from 'vue'
import { useRoute } from 'vue-router'

// 결제 pinia
// const payMentStore = usePayMentStore()
// const payinfo = computed(() => payMentStore.payProduct)


// JSON 문자열을 객체로 변환
// const cartItems = route.params.item ? route.params.item.split(',') : [];
const route = useRoute()
const cartData = JSON.parse(decodeURIComponent(route.query.item))
console.log('받은 배열', cartData.purchaseProductDtos)
console.log('받은 총 가격', cartData.totalPrice)

const merchant_uid = `IMP${Date.now()}` // 결제외부API 키 (항사 새로이 생성된다.)

// 검증 순서
// 1. "장바구니의 상품 총가격(purchase 테이블의 totalPrice)"이랑 , 장바구니의 "총 결제금액"을 비교 검증(삼품의 가격은 DB에 존재하기에)
// 2. "결제창의 총 상품 금액"과 장바구니의 상품 총가격(purchase 테이블의 totalPrice)"랑 비교 / 하기 위해서 저장한다
// 사후검증 준비 단계 

// 결국 나누어진 컴포넌트에 전역 상태관리를 위해서 
// "장바구니의 상품 총가격(purchase 테이블의 totalPrice)" 를 store에 저장해서 사용해야 한다.

const purchaseId = ref();
const purchaseStatus = ref(null);

watchEffect(() => {
  console.log('Watch triggered:', purchaseStatus.value)
  if (purchaseStatus.value) {
    console.log('PAID 상태 감지, 이벤트 발생')
    alert('결제가 완료되었습니다.')
    // SSE 연결을 초기화합니다.
    connectSSE()
    // window.location.reload()
  }
})

// 1번 검증
onMounted(async () => {
  const res = await axios.post(`${GLOBAL_URL}/api/purchase/create`, cartData, {
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${sessionStorage.getItem('token')}`,
    },
  })
  if (res.status == 200) {
    purchaseId.value = res.data.purchaseId; // 여서 id를 통해서 (purchase 테이블의 totalPrice) 추출
    console.log(res.data)
  } else {
    console.log('failed create order')
  }
  // 2번 검증
  const data = {
    merchant_uid: merchant_uid,
    amount: cartData.totalPrice,
    purchaseId: purchaseId.value
  }
    // 주문 상세 페이지 로드 완료되면 아래 호출
  try {
    const res = await axios.post(`${GLOBAL_URL}/api/payment/prepare`, data)
    if (res.status == 200) {
      console.log('사전검증 성공')
    } else {
      console.log('사전검증 실패')
    }
  } catch (error) {
    console.error('Payment prepare failed', error)
  }
})

const requestPay = async () => {
  const IMP = window.IMP
  IMP.init('imp25637745')
  // IMP.init(`${GLOBAL_IMP_KEY}`) // 키다
  IMP.request_pay(
    {
      pg: 'html5_inicis',
      pay_method: 'card',
      merchant_uid: merchant_uid,
      name: cartData.purchaseProductDtos.map((item) => item.name).join(','),
      amount: cartData.totalPrice,
      buyer_email: 'kdh7313@naver.com',
      buyer_name: '김태영',
      buyer_tel: '010-1234-5678',
      buyer_addr: '대구광역시 중구',
      buyer_postcode: '123-456'
    },
    async (rsp) => {
      if (rsp.success) {
        const data = {
          imp_uid: rsp.imp_uid,
          merchant_uid: rsp.merchant_uid,
          paid_amount: rsp.paid_amount,
          apply_num: rsp.apply_num,
          // email: email.value,
          purchaseId: purchaseId.value
        }
        // form.append('imp_uid', rsp.imp_uid)
        // form.append('merchant_uid', rsp.merchant_uid)
        // form.append('paid_amount', rsp.paid_amount)
        // form.append('apply_num', rsp.apply_num)
        // form.append('email', email.value)
        // form.append('purchaseId', purchaseId)

        try {
          // 사후 검증
          await axios
            .post(`${GLOBAL_URL}/api/payment/validate`, data, {
              headers: {
                'Content-Type': 'application/json',
              }
            })
            .then(async (res) => {
              console.log(res.data)

              const mesg = '결제가 완료되었습니다.'
              const buyerInfo = {
                merchant_uid: rsp.merchant_uid,
                imp_uid: rsp.imp_uid,
                buyerEmail: rsp.buyer_email,
                pay_method: rsp.pay_method,
                embPgProvider: rsp.emb_pg_provider,
                pgProvider: rsp.pg_provider,
                paidAt: rsp.paid_at,
                status: rsp.status,
                amount: rsp.paid_amount // 총 결제 금액
                // phone: rsp.buyer_tel,
                // addr: rsp.buyer_addr,
                // post: rsp.buyer_postcode
              }

              console.log(buyerInfo)

              // 결제 정보 저장
              await axios
                .post(`${GLOBAL_URL}/api/payment/save-user-info`, buyerInfo, {
                  headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${sessionStorage.getItem('token')}`,
                  }
                })
                .then((res) => {
                  console.log(res.data)
                })
                .catch((e) => {
                  console.log(e)
                })

              const purchaseInfo = {
                imp_uid: rsp.imp_uid,
                merchant_uid: rsp.merchant_uid,
                buyerEmail: rsp.buyer_email,
                buyerName: rsp.buyer_name,
                pay_method: rsp.pay_method,
                amount: rsp.paid_amount,
                buyerPhone: rsp.buyer_tel,
                buyerAddr: rsp.buyer_addr,
                buyerPostcode: rsp.buyer_postcode,
                purchaseProductDtos: cartData.purchaseProductDtos // 제품IDs와 수량들
              }

              // 주문 정보 저장
              await axios
                .post(`${GLOBAL_URL}/api/payment/save-purchase-info`, purchaseInfo, {
                  headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${sessionStorage.getItem('token')}`,
                  }
                })
                .then((res) => {
                  console.log(res.data)
                  purchaseStatus.value = 'PAID'
                  // window.location.href = 'purchaseCompleted?merchant_uid=' + res.data
                })
                .catch((e) => {
                  console.log(e)
                })
            })
            .catch((e) => {
              console.log(e)
            })
        } catch (error) {
          console.error('Payment processing failed', error)
        }
      } else {
        alert('결제에 실패하였습니다.')
      }
    }
  )
}
// 알림 이벤트 소스 (항시 대기중)
const connectSSE = () => {
  const sse = new EventSource(`${GLOBAL_URL}/api/notification/payment/completed/subscriber`,{
    headers: {
        'Content-Type': 'text/event-stream',
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      },
  })
  console.log('start sse')
  console.log(sse)

  sse.addEventListener('paymentCompletedEvent', async (event) => {
    console.log('SSE event received:', event.data)
    const data = await JSON.parse(event.data)
    purchaseStatus.value = event.data.status
    console.log('Message:', data.message)
    console.log('Order ID:', data.orderId)
    console.log('Total Amount:', data.totalAmount)
  })

  sse.addEventListener('error', (event) => {
    if (event.readyState === EventSource.CLOSED) {
      console.error('SSE connection was closed.')
    } else {
      console.error('Error occurred:', event)
    }
  })
}
const payroute = useRoute();
const payData = JSON.parse(decodeURIComponent(payroute.query.item));
</script>


<template>
  <section id="payment_wrapper">
    <h1>결제하기</h1>
    <PayUserInfo></PayUserInfo>

    <h2>주문 상품</h2>
    <PayProduct :productInfo="payData"></PayProduct>
    
    <h2>결제금액</h2>
    <div class="line"></div>
    <PayMoney></PayMoney>

    <div class="line"></div>

    <h2>결제수단</h2>
    <PayMethod></PayMethod>

    <div class="btn_case"><button class="pay_btn" type="submit" @click="requestPay">결제하기</button></div>
  </section>
</template>

<style scoped>
#payment_wrapper {
  max-width: 1064px;
  width: 100%;
  margin: 0 auto;
}
h1{
  font-size: 4rem;
  font-weight: 400;
  padding: 50px 0 20px 0;
  border-bottom: 2px #000 solid;
}
h2 {
  font-size: 2.2rem;
  margin: 50px 0 20px 0;
}
.btn_case{
  margin-top: 100px;
  width: 100%;
  text-align: center;
}
.pay_btn{
    padding: 10px;
    width: 170px;
    height: 45px;
    background-color: rgb(240, 240, 240);
    border-radius: 1.2rem;
    text-align: center;
    font-size: 1.5rem;
    font-weight: 600;
}
.pay_btn:hover{
  background-color: var(--color-main-bloode);
  color: #a7ae9c;
}
.line{
  width: 100%;
  height: 2px;
  background-color: black;
}
</style>
