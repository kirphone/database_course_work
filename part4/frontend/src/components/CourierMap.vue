<template>
  <div>
    <GoogleMap
        :api-key="mapApiKey"
        style="width: 100%; height: 500px"
        :center="center"
        :zoom="15"
        ref="mapRef">
      <Marker v-on:click="step(mar[1])" v-for="mar in markers" :key="mar.id"
              :options="{ position: {lat : mar[0].addressLat, lng: mar[0].addressLng} }"/>
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
    step: function (orderId) {
      this.$store.commit('setOrderId', orderId)
      this.$router.push("/order-get")
    }
  },
  mounted() {
    this.downloadApiKey()
    axios_config.get("/user/orders/all").then(resp => {
      this.markers = resp.data
      console.log(resp.data)
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