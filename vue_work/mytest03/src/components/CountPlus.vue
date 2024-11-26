<template>
    <div class="position">
        <button @click="countDown"> - </button>
        <h1> 값: {{ count }} </h1>
        <button @click="countUp"> + </button>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const count = ref(0)
const countDown = () =>{
    count.value -= 1
}
const countUp = () =>{
    count.value += 1
}
</script>

<style scoped>
.position{
    display: flex;
    gap: 2%;
}
</style>