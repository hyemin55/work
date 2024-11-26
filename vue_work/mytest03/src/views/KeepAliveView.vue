<script setup>
import { onBeforeUnmount, onMounted, onUnmounted, shallowRef } from 'vue';
import AboutView from './AboutView.vue';
import HomeView from './HomeView.vue';
import ComputedView from './ComputedView.vue';

/* 
component는 반응형으로 만들면 안되는 데이터유형이다.
왜냐하면 ref로 반응형객체로 만들면 성능 상 오버헤드가 발생할 수 있다.
그러므로 반응형 처리를 하지 않도록 하는 방법이 2가지 있는데
1. markRaw -> 컴포넌트를 반응형 처리 대상에서 제외한다.
2. shallowRef -> 컴포넌트 객체를 반응형 처리하지 않는다.(객체의 최상위 레벨만 반응형으로 유지한다.)
*/

const currentTab = shallowRef(AboutView);
const currentTabMap = (clickTab) =>{
    currentTab.value = clickTab
}

// 초기 렌더링을 진행하고 마운트 되기전에 실행된다.
onMounted(()=>{
    console.log('onMounted됨')
})
// 마운트가 해제될 때 실행된다.
onUnmounted(()=>{
    console.log('onUnmounted@@!!')
})
onBeforeUnmount(()=>{
    console.log('onBeforeUnmount')
})
</script>

<template>
  <div >
    <button @click="currentTabMap(AboutView)">AboutView</button>
    <button @click="currentTabMap(HomeView)">HomeView</button>
    <button @click="currentTabMap(ComputedView)">ComputedView</button>

    <!-- KeepAlive로 탭 컴포넌트 캐싱 -->
     <!-- 
    KeppAlive가 있기때문에 onMounted는 처음에만 실행되고
    탭 이동을 할 때엔 마운트가 그냥 비활성화만 되기때문에 onMounted가 실행되지않는다. 
       -->
    <KeepAlive :max="0">
      <component :is="currentTab" />
    </KeepAlive>
  </div>
</template>
<!-- 
LRU란?
가장 최근에 사용된 데이터를 캐시한다.
MRU란?
가장 최근에 사용된 데이터를 먼저 버린다.
:max = '5'
최대 캐시가 넘어가면 과거의 캐시를 삭제하고 최근 캐시를 저장한다.
 -->