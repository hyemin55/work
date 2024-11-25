<script setup>
import { onMounted, onUnmounted, ref, watch } from "vue";
import { Carousel, Pagination, Slide } from "vue3-carousel";
import home_1 from "@/assets/img/c008.png";
import home_2 from "@/assets/img/d003.png";
import home_3 from "@/assets/img/c008.png";
import home_4 from "@/assets/img/d003.png";
import home_5 from "@/assets/img/c008.png";
import "vue3-carousel/dist/carousel.css";
import { preventDefault } from "vant/lib/utils";

const slides = ref([
    { id: 1, title: '향수1', img: home_1,},
    { id: 2, title: '향수2', img: home_2 },
    { id: 3, title: '향수3', img: home_3 },
    { id: 4, title: '향수4', img: home_4 },
    { id: 5, title: '향수5', img: home_5 },
]);

const slideBox = document.querySelector('.slide_box');
const slide = () => {
	slideBox.style.transform = 'translateX(-350px)';
};
</script>

<template>
	<section class="wrapper">
		<button @click="slide">테스트용</button>
		<article class="slideLine">
			
			<div class="slide_box" v-for="(slide, index) in slides" :key="slide.id">
				<img class="slideImg" :src="slide.img" />
			</div>

		</article>
	</section>
</template>

<style scoped>
.wrapper{
	width: 1280px;
	height: 560px; /* 임시 */
	overflow: hidden;
}
.slideLine{
	width: 2060px;
	height: 100%;
	overflow: hidden;
	display: flex;
	align-items: flex-end;
}
.slide_box{
	height: 350px;
	width: 350px;
	display: flex;
	align-items: center;
	justify-content: center;
	background-color: lightgray;
	margin-right: 20px;
}
.slide_box:nth-child(1){
	width: 560px !important;
	height: 560px !important;
}
.slide_box img{
	height: 80%;
}
</style>