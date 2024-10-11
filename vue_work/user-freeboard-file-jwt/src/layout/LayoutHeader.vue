

<template>
  <header>
    <div class="wrapper p-5 text-white text-3xl bg-yellow-400">
      <nav class="flex justify-between">
        <div class="flex space-x-5">
          <div>
            <RouterLink to="/">Home</RouterLink>
          </div>
          <div>
            <RouterLink to="/userlist">User</RouterLink>
          </div>
          <div class="group relative">
            <RouterLink to="/freeboardlist">FreeBoard</RouterLink>

            <div class="hidden group-hover:block absolute bg-yellow-400 p-5 rounded opacity-90">
              <div>
                <RouterLink to="/freeboardlist" class="hover:bg-yellow-400">FreeBoardList</RouterLink>
              </div>
              <div>
                <RouterLink to="/freeboardinput" class="hover:bg-yellow-400">FreeBoardInput</RouterLink>
              </div>
            </div>
          </div>
          <div class="group relative">
            <RouterLink to="/fileupload">File</RouterLink>
            <div
              class="hidden group-hover:block absolute bg-yellow-400 p-5 rounded opacity-90"
            >
              <div>
                <RouterLink to="/fileupload">Fileupload</RouterLink>
              </div>
              <div>
                <RouterLink to="/fileuploads">Fileuploads</RouterLink>
              </div>
              <div>
                <RouterLink to="/filedownload">Filedownload</RouterLink>
              </div>
            </div>
          </div>
        </div>
        <template v-if="loginCheck">
          <div>
            LoginCheck
        </div>

      </template>
      <template v-else>
        <div class="flex space-x-5">
          <div>
            <RouterLink to="/join">Join</RouterLink>
          </div>
          <div>
            <RouterLink to="/login">Login</RouterLink>
          </div>
        </div>
      </template>
      </nav>
    </div>
  </header>

</template>

<script setup>
import { doLogincheck } from '@/api/loginApi';
import { ref, watchEffect } from 'vue';
import { RouterLink } from 'vue-router';

const loginCheck = ref(false);

watchEffect(() => {
  const result = doLogincheck();
  if(result==false){
    loginCheck.value = false;
  }else{
      console.log(result);
      loginCheck.value = true;
  }
})
</script>

<style lang="scss" scoped></style>
