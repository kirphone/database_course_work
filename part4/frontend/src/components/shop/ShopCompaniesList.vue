<template>
  <h1 class="h1">Магазины</h1>
  <h1 class="h1" style="color:red;">{{ errorMessage }}</h1>
  <div class="wrapper">
    <div v-on:click="select_shop(item.name)" class="item" v-for="item in companies" :key="item.name">
      {{ item.name }}
    </div>
  </div>
</template>

<script>
import axios_config from "@/axios_config";

export default {
  name: "ShopCompaniesList",
  data() {
    return {
      errorMessage: "",
      companies: []
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData: function () {
      axios_config.get('shop/getShopCompanies')
          .then(response => {
            console.log(response.data)
            this.companies = response.data
          })
          .catch(e => {
            if (e.response.status === 401) {
              this.$store.commit("removeToken")
              this.$router.push("/auth");
            } else {
              this.errorMessage = "Магазины не найдены"
              //this.$router.push("/error");
            }
          });
    },
    select_shop: function (shopName) {
      this.$store.commit("selectShop", shopName)
      this.$router.push("/select-shop");
    }
  }
}
</script>

<style scoped>
.item {
  padding: 16px;
  margin: 16px;
  width: 75%;
  background: #FFFFFF;
  /* Basic Shadow */

  box-shadow: 0px 4px 18px rgba(0, 0, 0, 0.1);
  border-radius: 14px;
}

.wrapper {
  display: grid;
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
</style>