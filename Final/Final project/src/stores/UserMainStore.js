import { defineStore } from 'pinia';

export const userMainStore = defineStore('userMainStore', {
  state: () => ({
    isExpended: false,

  }),
  actions: {
    toggleExpend() {
      console.log(this.isExpended)
      this.isExpended = !this.isExpended;
    },

  },
});
