const { defineStore } = require('pinia')

export const usePayMentStore = defineStore('payment', {
  state: () => ({
    productidx: ref([]),
  }),
  actions: {
    productScan() {},
  },
})
