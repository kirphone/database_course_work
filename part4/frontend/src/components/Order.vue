<template>
  <h1>Ваши заказы</h1>
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
      <td>{{ item.courier != null ?  item.courier.name : '' }}</td>
      <td>{{ item.courier != null ?  item.courier.phone : '' }}</td>
      <td v-on:click="message(item.id)">go to messages</td>
      <td>{{ item.status.name }}</td>
    </tr>
  </table>
</template>

<script>
import axios_config from "@/axios_config";

export default {
  name: "Order",
  data() {
    return {
      orders: []
    }
  },
  methods: {
    message: function (id) {
      this.$router.push(`/message/${id}`)
    },
    fetch: function () {
      this.orders = axios_config.get("/user/orders")
          .then(resp => {
            console.log(resp.data)
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
  },
  mounted: function () {
    window.setInterval(() => {
      this.fetch()
    }, 10000)
  }
}
</script>

<style scoped>

.row {
  margin: 16px;
}

.but {
  margin: 16px;
  font-size: 16px;
  width: 208px;
  height: 64px;
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