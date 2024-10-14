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
                <RouterLink to="/freeboardlist" class="hover:bg-yellow-400"
                  >FreeBoardList</RouterLink
                >
              </div>
              <div>
                <RouterLink to="/freeboardinput" class="hover:bg-yellow-400"
                  >FreeBoardInput</RouterLink
                >
              </div>
            </div>
          </div>
          <div class="group relative">
            <RouterLink to="/fileupload">File</RouterLink>
            <div class="hidden group-hover:block absolute bg-yellow-400 p-5 rounded opacity-90">
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
        <template v-if="loginPinia.loginCheck">
          <div class="flex space-x-5">
            <h1>{{ loginPinia.name }}님 반가워요😊</h1>
            <button @click="logout()">Logout</button>
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
import { doLogincheck } from '@/api/loginApi'
import router from '@/router'
import { useLoginStore } from '@/store/loginPinia'
import { watchEffect } from 'vue'
import { RouterLink } from 'vue-router'

const loginPinia = useLoginStore()
const logout = ()=>{
  localStorage.removeItem("token");
  loginPinia.logout();
}


watchEffect(async () => {
  const result = await doLogincheck()
  if (result === false) {
    loginPinia.logout()
    console.log(loginPinia)
    router.push({ name: 'login' })
  } else {
    if (result.status == 200) {
      loginPinia.login(result.data)
    } else if (result.status == 401) {
      localStorage.removeItem('token')
      loginPinia.logout();
    }
    // loginCheck.value = true;
  }
})
</script>

<style lang="scss" scoped></style>
