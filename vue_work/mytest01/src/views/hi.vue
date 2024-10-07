<template>
    <div>
        <div id="A" @click="divA">
            <div id="B" @click="divB">
                <div id="c" @click="divC">
                    <h1>button</h1>
                    <button @click.stop="btnClick">button</button>
                    <a href="http://www.naver.com" @click.prevent="">naver</a>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
const divA = ()=>{
    console.log('divA');
}
const divB = ()=>{
    console.log('divB');
}
const divC = ()=>{
    console.log('divC');
}
const btnClick = ()=>{
    console.log('btnClick');
}
</script>

<style scoped>
div{ width: 60%; padding: 2rem;}
#A{ background-color: #ccc}
#B{ background-color: #999}
#C{ background-color: #000; height: 100;;}
</style>