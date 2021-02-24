<template>
  <h1>Ваши заказы</h1>
  <button class="but-1" v-on:click="fetch()">Reload</button>
  <table>
    <tr>
      <th>Id заказа</th>
      <th>Имя курьера</th>
      <th>Телефон курьера</th>
      <th>Сообщения</th>
      <th>Статус</th>
    </tr>

    <tr :key="item.id" v-for="item in orders">
      <td>{{ item.id }}</td>
      <td>{{ item.courier != null ? item.courier.name : '' }}</td>
      <td>{{ item.courier != null ? item.courier.phone : '' }}</td>
      <td v-on:click="fetchMessages(item.id, item.courier)">
        <button>open messages</button>
      </td>
      <td>{{ item.status.name }}</td>
    </tr>
  </table>

  <button class="but-1" @click="shopMessage()">Close messages</button>
  <div class="messages" v-if="showMessageOrder">
    <button class="but-1" @click="reFetchMess()">Обновить</button>
    <div class="mess">{{ messages.length === 0 ? 'Нету сообщений' : '' }}</div>
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
  name: "Order",
  data() {
    return {
      mess: "",
      messages: 123,
      showMessageOrder: false,
      orders: [],
      selectedOrderId: 0,
      courierMessageId: 0,
    }
  },
  methods: {
    sendMessage: function () {
      axios_config.post(`/user/order/${this.selectedOrderId}/messages`, this.mess )
      this.mess = ''
      this.reFetchMess()
    },
    reFetchMess: function () {
      if (this.selectedOrderId === 0 && this.courierMessageId === 0) {
        return
      }
      axios_config.get(`user/order/${this.selectedOrderId}/messages`)
          .then(resp => {
            this.messages = resp.data
          })
          .catch(() => {
            this.messages = []
          })
    },
    fetchMessages: function (id, courier) {
      this.selectedOrderId = id
      this.showMessageOrder = true
      if (courier != null) {
        this.courierMessageId = courier.id
        this.showMessageOrder = true
        this.reFetchMess()
      } else {
        this.messages = []
      }
    },
    shopMessage: function () {
      this.showMessageOrder = false
    },
    fetch: function () {
      this.orders = axios_config.get("/user/orders")
          .then(resp => {
            this.orders = resp.data
          })
          .catch(e => {
                if (e.response.status === 401) {
                  this.$store.commit("removeToken")
                  this.$router.push("/auth");
                } else {
                  this.errorMessage = "Магазины не найдены"
                  this.$router.push("/error");
                }
              }
          )
    }
  },
  created: function () {
    this.fetch();
  }
}
</script>

<style scoped>

.in {
  height: 32px;
  width: 160px;
}

.bor {
  border: 1px solid;
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