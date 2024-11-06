import { defineStore } from 'pinia'

export const productDetailStore = defineStore('productDetailStore', {
  state: () => ({
    productIdx: 0,
    productSize: 0,
    reviewCount: 0,
    starAverage: 0,
  }),
  actions: {
    setIdx(idx, size) {
      this.productIdx = idx
      this.productSize = size
      console.log('피니아스토어의 this.productIdx = ', this.productIdx)
      console.log('피니아스토어의 this.productIdx = ', this.productSize)
    },
    setReview(reviewCount, starAverage) {
      this.reviewCount = reviewCount
      this.starAverage = starAverage.toFixed(1)

      console.log('피니아스토어의 this.reviewCount = ', this.reviewCount)
      console.log('피니아스토어의 this.starAverage = ', this.starAverage)
    },
  },
})
