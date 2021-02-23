<template>
  <h1 class="h1">Выберите роль</h1>

  <h2 class="h2">Вы сможете сменить её позже</h2>
  <div v-on:click="chooserRole(false)" class="role" v-bind:class="{'selected-windows' : isClient === false}">
    <img class="img" :src="require('../../assets/images/cou.svg')" alt="Курьер">
    <div class="inner_text">Курьер</div>
  </div>

  <div v-on:click="chooserRole(true)" class="role1" v-bind:class="{'selected-windows' : isClient === true}">
    <img class="img" :src="require('../../assets/images/client.svg')" alt="Клиент">
    <div class="inner_text">Клиент</div>
  </div>

  <div class="button" v-on:click="next()">
    <div :class="{'text-not-selected':isClient === null, 'text-selected':isClient != null }">Далее</div>
  </div>


</template>

<script>
export default {
  name: "ChooseRole",
  data() {
    return {
      isClient: null
    }
  },
  methods: {
    chooserRole: function (role) {
      this.isClient = role;
    },
    next : function () {
      if(this.isClient != null){
        if(this.isClient){
          this.$store.commit("isClient", false);
          this.$router.push("orders-courier");
        }else{
          this.$store.commit("isClient", true);
          this.$router.push("make-order");
        }
      }
    }
  },
}
</script>

<style scoped>

.selected-windows {
  background: #FFFFFF !important;
  /* Special Shadow */

  box-shadow: 0 4px 18px rgba(108, 99, 255, 0.45) !important;
  border-radius: 10px !important;
}

.text-selected {
  font-family: Roboto;
  font-style: normal;
  font-weight: bold;
  padding-top: 20px !important;
  font-size: 30px;
  line-height: 23px;
  text-align: center;

  height: 80px;
  background: #6C63FF;
  border-radius: 10px;
}

.text-not-selected {
  font-family: Roboto;
  font-style: normal;
  font-weight: bold;
  padding-top: 20px !important;
  font-size: 30px;
  line-height: 23px;
  text-align: center;

  height: 80px;

  background: rgba(108, 99, 255, 0.6);
  border-radius: 10px;
}

.button {
  color: #ffffff;
  position: absolute;
  width: 399px;
  left: 750px;
  top: 831px;
}

.role1 {
  position: absolute;

  left: 1292px;
  top: 361px;

  background: #FFFFFF;
  /* Basic Shadow */

  box-shadow: 0px 4px 18px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
}

.img {
  width: 262px;
  height: 261.67px;
  left: 323px;
  top: 374.33px;

  border-radius: 90px;
}

.inner_text {
  /* Курьер */

  font-family: Roboto;
  font-style: normal;
  font-weight: normal;
  font-size: 24px;
  line-height: 28px;
  text-align: center;

  margin-bottom: 15px !important;

  color: #000000;
}

* {
  overflow-x: hidden;
  padding: 0 !important;
  margin: 0 !important;
}

.h1 {
  /* Выберите роль */
  margin-top: 32px !important;
  text-align: center;

  font-family: Roboto;
  font-style: normal;
  font-weight: bold;
  font-size: 64px;
  line-height: 75px;
  /* identical to box height */


  color: #000000;
}

.h2 {
  margin-top: 32px !important;
  text-align: center;


  font-family: Roboto;
  font-style: normal;
  font-weight: normal;
  font-size: 24px;
  line-height: 34px;
  /* identical to box height, or 142% */
  color: #000000;
}

.role {
  position: absolute;

  left: 323px;
  top: 361px;

  background: #FFFFFF;
  /* Basic Shadow */

  box-shadow: 0px 4px 18px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
}
</style>