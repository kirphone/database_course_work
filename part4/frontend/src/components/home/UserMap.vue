<template>
  <GoogleMap
      :api-key="googleMapApiKey"
      style="width: 100%; height: 500px"
      :center="center"
      :zoom="15"
      ref="mapRef"
  >
    <Marker v-on:click="step(mar.id)" v-for="mar in markers" :key="mar.id" :options="{ position: {lat : mar.address.addressLat, lng:mar.address.addressLng} }"/>
  </GoogleMap>
</template>

<script>
import {GoogleMap, Marker} from "vue3-google-map";
import {defineComponent} from "vue";
import axios_config from "@/axios_config";

export default defineComponent({
  name: "UserMap",
  components: {GoogleMap, Marker},
  props: {
    googleMapApiKey: String
  },
  data() {
    return {
      markers: []
    }
  },
  setup() {

  },
  methods:{
    step : function (shopId) {
      console.log(shopId)
      this.$store.commit('setShopId',shopId)
      this.$router.push("/basket")
    }
  },
  mounted() {
    axios_config.get("/shop/all").then(resp => {
      this.markers = resp.data
    })
    navigator.geolocation.getCurrentPosition(pos => {
      this.center = {lat: pos.coords.latitude, lng: pos.coords.longitude};
    }, err => {
      console.log(err);
    })
  }
})
</script>

<style scoped>

</style>