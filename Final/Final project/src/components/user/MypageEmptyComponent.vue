<script setup></script>

<template>
    <div class="layout">
        <img src="/src/assets/img/empty_box.png" alt="">
        <p class="text">목록이 비어있습니다.</p>
    </div>
</template>

<style scoped>
.layout{
    width: 100%;
    height: 300px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    background-color: #fff;
    position: relative;
}
.layout img{
    width: auto;
    height: 50%;
}
.text{
    font-size: 2rem;
    font-weight: 600;
    color: #36474f;
}
    
</style>