<template>
  <h1>Продукты</h1>
  <h1>Стоимость корзины : {{ price }}</h1>
  <table>
    <tr>
      <th>Название</th>
      <th>Описание</th>
      <th>Категория</th>
      <th>Цена</th>
      <th>В корзине</th>
      <th>Действия</th>
    </tr>

    <tr :key="item.id.productId" v-for="item in product">
      <td>{{ item.product.name }}</td>
      <td>{{ item.product.description }}</td>
      <td>{{ item.product.category.name }}</td>
      <td>{{ item.price }}</td>
      <td>{{ hashTable[item.id.productId] }}</td>
      <td>
        <button v-on:click="add(item.id.productId,item.price)">Add</button>
        <button v-on:click="remove(item.id.productId,item.price)">Delete</button>
        <input type="checkbox" @click="addConfirm(item.id.productId)"/>
      </td>
    </tr>
  </table>
  <div class="row">
    <input type="text" max="500" min="0" v-model="priceDelivery">
    <input type="range" min="0" max="500" step="1" v-model="priceDelivery">
  </div>
  <h1>Всего : {{ price + (isNaN(priceDelivery) || priceDelivery === "" ? 0 : Number.parseInt(priceDelivery)) }}</h1>
  <h1 style="color: red">{{ errorMessage }}</h1>
  <button v-on:click="next()" class="but">
    <div>Оформить заказ</div>
  </button>
</template>

<script>
import axios_config from "@/axios_config";
import store from "../../store";

export default {
  name: "Basket",
  methods: {
    add: function (id, price) {
      if (this.hashTable[id] == null) {
        this.hashTable[id] = 1
      } else {
        this.hashTable[id] += 1;
      }
      this.price += price;
    },
    remove: function (id, price) {
      //console.log(this.hashTable)
      if (this.hashTable[id] != null && this.hashTable[id] > 0) {
        this.price -= price;
        this.hashTable[id] -= 1;
      }
    },
    addConfirm: function (id) {
      if (this.confirm[id] == null) {
        this.confirm[id] = true
        return
      }
      this.confirm[id] = !this.confirm[id]
    },
    next: function () {
      if (isNaN(this.priceDelivery) || this.priceDelivery > 500) {
        this.errorMessage = "Неккоректная цена доставки"
      } else if (this.priceDelivery < 0) {
        this.errorMessage = "Неккоректная цена доставки"
      } else {
        navigator.geolocation.getCurrentPosition(pos => {
          var a = {lat: pos.coords.latitude, lng: pos.coords.longitude};
          console.log(a)
          var products = []
          this.hashTable.forEach((val, key) => {
            products.push({
              "productId": key,
              "productCount": val,
              "needConfirm": (this.confirm[key] != null)
            })
          })
          axios_config.post('/user/orders', {
            'lat': a.lat,
            'lng': a.lng,
            'shopId': this.$store.state.shopId,
            'products': products
          }).then(resp => {
                console.log(resp.data)
                this.$router.push("/orders")
              }
          )
        }, err => {
          console.log(err);
        })
      }
    }

  },
  created() {
    axios_config.get(`/shop/${store.state.shopId}/products`)
        .then(response => {
          console.log(response.data)
          this.product = response.data
        })
        .catch(e => {
              if (e.response.status === 401) {
                this.$store.commit("removeToken")
                this.$router.push("/auth");
              } else {
                this.$router.push("/error");
              }
            }
        )
  },
  data() {
    return {
      confirm: [],
      errorMessage: "",
      priceDelivery: 50,
      price: 0,
      hashTable: [],
      product: [
        {
          'id': 123,
          'name': 'Творого',
          'description': 'Живой',
          'category': 'Молочка',
          'price': 123.0,
        },
      ]
    }
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