<script setup>
// import { GLOBAL_URL } from '@/api/util'
// import axios from 'axios'
// import { onMounted, defineProps } from 'vue'

// const props = defineProps({
//   productId: {
//     type: Object,
//     required: true,
//   },
// })

// onMounted(async () => {
//   try {
//     const res = await axios.get(
//       `${GLOBAL_URL}/detail/images/${props.productId}`,
//     )
//     if (res.status == 200) {
//       console.log(res.data)
//     }
//     return res
//   } catch (e) {
//     console.error('실패', e)
//   }
// })
</script>

<template>
  <article id="ProductDescription">
    <img src="../../img/상세페이지_1.jpg" alt="" />
    <!-- <img :src="`${GLOBAL_URL}/api/file/download/${props.productId}`" alt="" /> -->
  </article>
</template>

<style scoped>
#ProductDescription {
  display: flex;
  justify-content: center;
  height: auto;
  width: 100%;
}
</style>
