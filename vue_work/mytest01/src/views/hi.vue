<template>
  <div>
      <p :style="myStyle">Lorem ipsum dolor sit amet consectetur adipisicing elit. Blanditiis libero, illo eius
          expedita minima suscipit quaerat iste aliquam, velit praesentium nemo explicabo ex ut qui reprehenderit
          quod. Officiis, asperiores tempore.</p>
      <button @click="fontIncre">font++</button>
      <button @click="fontSize--">font--</button>
  </div>
</template>

<script setup>
import { computed,ref } from 'vue';
const fontSize = ref(13);
const fontIncre = ()=>{
  console.log(fontSize)
  fontSize.value++;
}
const myStyle = computed(() => {
  return {
      color: 'red',
      fontSize: fontSize.value+'px'
  }
})
</script>

<style lang="scss" scoped></style>