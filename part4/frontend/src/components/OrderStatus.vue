<template>
  <button @click="takeOrder()">Take order</button>


  <div class="messages">
    <button class="but-1" @click="reFetchMess()">Обновить</button>
    <div class="mess" @v-if="messages.length !== 0" :key="mess" v-for="mess in messages">
      <div class="bor"
           v-bind:class="{'left-mess' : mess.sender !== courierMessageId, 'right-mess' : mess.sender === courierMessageId }">
        {{ mess.text }}
      </div>
    </div>
    <div>
      <input class="but-1 in" type="text" v-model="mess">
      <button class="but-1" @click="sendMessage()">Send</button>
    </div>
  </div>
</template>

<script>
import axios_config from "@/axios_config";

export default {
  name: "OrderStatus",
  data() {
    return {
      mess: "",
      messages: [],
      showMessageOrder: false,
      orders: [],
      selectedOrderId: 0,
      courierMessageId: 0,
    }
  },
  methods: {
    takeOrder: function () {
      //axios_config.post().then()
    },
    sendMessage: function () {
      axios_config.post(`/user/order/${this.$store.state.orderId}/messages`, this.mess)
      this.mess = ''
      this.reFetchMess()
    },
    reFetchMess: function () {
      axios_config.get(`user/order/${this.$store.state.orderId}/messages`)
          .then(resp => {
            this.messages = resp.data
          })
          .catch(() => {
            this.messages = []
          })
    },
  },
  created() {
    this.reFetchMess()
  }
}
</script>

<style scoped>

.in {
  height: 32px;
  width: 160px;
}


.left-mess {

  padding: 15px;
  margin-right: 32px;
  text-align: left;
}

.right-mess {

  padding: 15px;
  margin-left: 32px;
  text-align: right;
}

.but-1 {
  margin: 16px;
}

.mess {
  margin: 16px;
}

.messages {
  margin: 32px;
  border: 1px solid;
}

h1 {
  margin: 5px 5px 5px 16px;
}

.bor {
  border: 1px solid;
}

.but-1 {
  margin: 16px;
}

.mess {
  margin: 16px;
}

.messages {
  margin: 32px;
  border: 1px solid;
}

h1 {
  margin: 5px 5px 5px 16px;
}

table {
  margin: 15px;
  width: 95%;
}

table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}

th, td {
  padding: 15px;
  text-align: left;
}

#t01 tr:nth-child(even) {
  background-color: #eee;
}

#t01 tr:nth-child(odd) {
  background-color: #fff;
}

#t01 th {
  background-color: black;
  color: white;
}

</style>