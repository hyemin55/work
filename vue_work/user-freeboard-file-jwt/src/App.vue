<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

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
              <RouterLink to="/freeboardlist">FreeBoardList</RouterLink>
            </div>
            <div>
              <RouterLink to="/freeboardinput">FreeBoardInput</RouterLink>
            </div>
          </div>
        </div>
        <div>
          <RouterLink to="/fileupload">FileUpload</RouterLink>
        </div>
        <div>
          <RouterLink to="/filedownload">Filedownload</RouterLink>
        </div>
      </div>
        <div class="flex space-x-5">
        <div>
          <RouterLink to="/join">Join</RouterLink>
        </div>
        <div>
          <RouterLink to="/login">Login</RouterLink>
        </div>
      </div>
      </nav>
    </div>
  </header>

  <RouterView />
</template>

<style scoped></style>
