<template>
  <div>
    <UserMap :google-map-api-key="mapApiKey"/>
  </div>
</template>

<script>
import UserMap from "@/components/home/UserMap";
import axios_config from "@/axios_config";

export default {
  name: "Home",
  components: {UserMap},
  data(){
    return {
      mapApiKey : ""
    }
  },
  methods: {

  },
  setup() {
    const downloadApiKey = function (){
      axios_config.get('map/api_key')
          .then(response => {
            this.mapApiKey = response.data;
          })
    }

    return {downloadApiKey}
  },
  mounted() {
    this.downloadApiKey()
  }
}
</script>

<style scoped>

</style>