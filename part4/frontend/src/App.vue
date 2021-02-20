<template>
  <LoginAndRegistrationForm/>
</template>

<script>

import LoginAndRegistrationForm from "@/components/auth/LoginAndRegistrationForm";
import store from "@/store";

export default {
  name: 'App',
  store,
  components: {
    LoginAndRegistrationForm
  }
}
</script>

<style>
@import url("assets/css/form.css");

@font-face {
  font-family: ElMessiri;
  src: url('assets/fonts/ElMessiri-Bold.ttf');
  font-weight: bold;
  font-style: normal;
}

@font-face {
  font-family: ElMessiri;
  src: url('assets/fonts/ElMessiri-Regular.ttf');
  font-weight: normal;
  font-style: normal;
}

* {
  box-sizing: border-box;
  font-family: ElMessiri, 'Comic Sans MS', fantasy;
  font-weight: normal;
}

body {
  margin: 0;
  padding: 0;
  background: #e6f4fd;
}
</style>
