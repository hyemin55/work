import { defineStore } from 'pinia'

export const usePayMentStore = defineStore('payment', {
  state: () => ({
    payProduct: ref([]),
  }),
  actions: {
    payProductScan(item) {
      this.payProduct.push(item);
    },
  },


})
