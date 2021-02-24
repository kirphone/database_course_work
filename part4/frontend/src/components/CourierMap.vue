<template>
  <div>
    <GoogleMap
        :api-key="mapApiKey"
        style="width: 100%; height: 500px"
        :center="center"
        :zoom="15"
        ref="mapRef">
      <Marker v-on:click="step(mar.id)" v-for="mar in markers" :key="mar.id"
              :options="{ position: {lat : mar.addressLat, lng: mar.addressLng} }"/>
    </GoogleMap>
  </div>
</template>

<script>
import axios_config from "@/axios_config";
import {GoogleMap, Marker} from "vue3-google-map";

export default {
  name: "CourierMap",
  components: {GoogleMap, Marker},
  data() {
    return {
      mapApiKey: "",
      markers: []
    }
  },
  methods: {
    step: function (shopId) {
      this.$store.commit('setShopId', shopId)
      this.$router.push("/order-get")
    }
  },
  mounted() {
    this.downloadApiKey()
    axios_config.get("/user/orders/all").then(resp => {
      this.markers = resp.data
      console.log(this.markers)
    })
    navigator.geolocation.getCurrentPosition(pos => {
      this.center = {lat: pos.coords.latitude, lng: pos.coords.longitude};
    }, err => {
      console.log(err);
    })
  },
  setup() {
    const downloadApiKey = function () {
      axios_config.get('map/api_key')
          .then(response => {
            this.mapApiKey = response.data;
          })
    }
    return {downloadApiKey}
  },
}

</script>

<style scoped>

</style>