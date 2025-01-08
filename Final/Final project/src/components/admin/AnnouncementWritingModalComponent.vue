<script setup>
import { ref } from 'vue';


const title = ref('')
const content = ref('')
    // 모달창 완료버튼(부모로 event 전달)
    // 부모자에서 @sucess 이벤트를 발생시킴
    const emit = defineEmits();
const props = defineProps({
  announcementDataCount:{
    type:Number,
    Required:true
  }
});
console.log(new Intl.DateTimeFormat('ko-KR',{year: 'numeric',month:'2-digit', day:'2-digit',hour:'numeric',minute:'numeric'}).format(new Date()))
console.log(props)
    const send = ()=>{
      const res = confirm('등록하시겠습니까?')
      if(res){
      const addAnnouncement = {
        number: props.announcementDataCount+1,
        header: title.value,
        content: content.value,
        creationDate: new Intl.DateTimeFormat('ko-KR',{year: 'numeric',month:'2-digit', day:'2-digit',hour:'numeric',minute:'numeric'}).format(new Date()),
        views: 0,
      }
      emit('closeModal', addAnnouncement)}
      else return
    }
  const cancelModal = () => {
  emit('closeModal',false);
};

</script>
<template>
    <section class="sale_modal">

        <article class="modal_page">
          <h2>공지사항 등록</h2>
            <table id="WritingTable">
              <thead>
                <tr class="title">
                  <td><input type="text" name="text" id="titleInputText" maxlength="100" placeholder="제목을 입력해주세요." v-model="title"></input></td>
                </tr>
                <tr class="content">
                  <td ><textarea name="content" id="contentInputText" placeholder="내용을 입력해주세요." v-model="content"></textarea></td>
                </tr>
              </thead>
            </table>
            <div class="Results_transfer_button">
        <button @click="send()">등록</button>
        <button @click="cancelModal()">취소</button>
      </div>
        </article>

        <article class="modal_background"></article>
    </section>
</template>

<style scoped>
/* 모달 전체 설정 ##################*/
.sale_modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100vh;
  z-index: 950;
  display: flex;
  align-items: center;
  justify-content: center;
}
/* 모달배경 설정 */
.modal_background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 10;
}
.modal_page {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
  /* 크기조절 여기서 */
  width: 800px;
  height: 600px;
  background-color: rgb(255, 255, 255);
  z-index: 11;
  border: 2px solid var(--color-main-bloode);
  padding: 5%;
  line-height: 2;
  font-size: 1.6rem;
  /* overflow-y: scroll; */
  /* scroll-behavior: smooth; */
}

#WritingTable{
width: 650px;
height: 400px;
box-shadow: 1px 1px 3px #333;
/* background-color: antiquewhite; */
}
tr{
  border: 0.5px solid var(--color-main-bloode);
  
}
.title{
  width: 100%;
  height: 50px;
}
input[type='text']{
  background-color: var(--color-main-pink);
  font-size: 1.8rem;
}
.content{
  width: 100%;
  height: 350px;
  /* background-color: brown; */
}
textarea,
input[type='text']{
  width: 100%;
  height: 100%;
  padding: 10px;
  border: none;
  resize: none;
}
textarea:focus,
input[type='text']:focus{
  outline: none;
  /* background-color: var(--color-main-Lgray); */
}
/* 전송, 취소 버튼 */
.Results_transfer_button {
  width: 70%;
  text-align: center;
}
.Results_transfer_button > button {
  font-size: 2rem;
  width: 30%;
  height: auto;
  padding: 0.5%;
  text-align: center;
  border-radius: 10px;
  margin:5% 8%;
  border: 2px solid var(--color-main-bloode);
}
.Results_transfer_button > button:hover {
  color: white;
  border: 2px solid var(--color-main-bloode);
  background-color: orange;
}
</style>