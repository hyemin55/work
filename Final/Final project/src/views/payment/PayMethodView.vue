<script setup>
import { ref } from 'vue';

    const paymentMethod = ref('');
    const selectCheck = (method)=>{
        paymentMethod.value = method;
    }
</script>

<template>
    <section id="pay_method">
        <article class="method_check">
            <div  @click="selectCheck('creditCard')">
                <label>신용카드</label>
                <input type="radio" name="paymentMethod" v-model="paymentMethod" value="creditCard" required>
            </div>
            <transition name="expand">
            <form v-if="paymentMethod === 'creditCard'">
                <div class="horizontal">
                    <p>카드선택</p>
                    <select id="request" name="request">
                        <option value="">신한카드</option>
                        <option value="">하나카드</option>
                        <option value="">국민카드</option>
                        <option value="">대구카드</option>
                        <option value="">기업카드</option>
                    </select>
                </div>
                <div class="horizontal">
                    <p>할부선택</p>
                    <input type="text" disabled placeholder="일시불">
                </div>
            </form>
            </transition>
        </article>
        
        <article class="method_check">
            <div @click="selectCheck('bankTransfer')">
                <label>계좌이체</label>
                <input type="radio" name="paymentMethod" value="bankTransfer" v-model="paymentMethod">
            </div>
            <transition name="expand">
            <form v-if="paymentMethod === 'bankTransfer'">
                <div class="horizontal">
                    <label>은행선택</label>
                    <select id="request" name="request">
                        <option value="">신한은행</option>
                        <option value="">하나은행</option>
                        <option value="">국민은행</option>
                        <option value="">대구은행</option>
                        <option value="">기업은행</option>
                    </select>
                </div>
                <div class="horizontal">
                    <label>입금자명</label>
                    <input type="text">
                </div>
            </form>
            </transition>
        </article>

        <article class="method_check">
            <div @click="selectCheck('easyPayment')">
                <label>간편결제</label>
                <input type="radio" name="paymentMethod" value="easyPayment" v-model="paymentMethod">
            </div>
            <transition name="expand">
            <div v-if="paymentMethod === 'easyPayment'">
                <div>카카오페이</div>
                <div>토스페이</div>
                <div>네이버페이</div>
            </div>
            </transition>
        </article>
    </section>
</template>

<style scoped>
#pay_method{
    /* 임시 높이 */
    height: 300px;
    width: 100%;
    /* width: 1064px; */
    position: relative;
}
.method_check{
    width: 100%;
    /* width: 1064px; */
    height: auto;
    background-color: rgb(197, 197, 197);
}

.method_check>div:nth-child(1){
    width: 100%;
    height: 64px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    background-color: rgb(138, 138, 138);
    border: 1px solid;
    z-index: 100;
    cursor: pointer;
}
.method_check>div:nth-child(2), form{
    z-index: 50;
}
.horizontal{
    display: flex;
    align-items: center;
    justify-content: left;
}


/* 트랜지션 효과 */
.expand-enter-from {max-height: 0; opacity: 0;} /* 초기값 */
.expand-enter-active {transition: max-height 1s ease, opacity 0.5s ease;}
.expand-enter-to {max-height: 500px; opacity: 1;}
.expand-leave-active {transition: max-height 0.5s ease, opacity 0s ease;}
.expand-leave-to {max-height: 0; opacity: 0;}
</style>