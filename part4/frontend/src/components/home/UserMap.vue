<template>
  <GoogleMap
      :api-key="googleMapApiKey"
      style="width: 100%; height: 500px"
      :center="center"
      :zoom="15"
      ref="mapRef"
  >
    <Marker :options="{ position: center }" />
  </GoogleMap>
</template>

<script>
import {GoogleMap, Marker} from "vue3-google-map";
import {defineComponent} from "vue";

export default defineComponent({
  name: "UserMap",
  components: { GoogleMap, Marker },
  props: {
    googleMapApiKey: String
  },
  data(){
    return {
      center: {}
    }
  },
  setup() {
    //let center = { lat: 40.689247, lng: -74.044502 }

    //return { center }
  },
  mounted() {
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