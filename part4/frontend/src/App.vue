<template>
  <div id="app">
    <base-header/>
    <router-view/>
  </div>
</template>

<script>

import store from "@/store";
import BaseHeader from "@/components/BaseHeader";

export default {
  name: 'App',
  store,
  components: {
    BaseHeader
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
  background: #FFFFFF;
}
</style>
