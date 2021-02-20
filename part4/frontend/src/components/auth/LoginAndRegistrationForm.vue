<template>
  <form @submit="validateForm">
    <div align="center">
      <h3 v-if="formName === 'login'">Форма входа</h3>
      <h3 v-else>Форма регистрации</h3>
    </div>
    <div class="form-row">
      <input v-model="email" type="text" id="email" maxlength="30" required>
      <label for="email">Email</label>
    </div>
    <div class="form-row">
      <input v-model="password" type="password" id="password" maxlength="50" required>
      <label for="password">Пароль</label>
    </div>

    <div v-if="formName === 'registration'" class="form-row">
      <input v-model="confirmPassword" type="password" id="confirmPassword" maxlength="50"
             required>
      <label for="confirmPassword">Подтвердите пароль</label>
    </div>

    <div v-if="formName === 'registration'" class="form-row">
      <input v-model="phone" type="tel" id="phone" maxlength="50"
             required>
      <label for="phone">Номер телефона</label>
    </div>

    <div v-if="formName === 'registration'" class="form-row">
      <input v-model="name" type="text" id="name" maxlength="50"
             required>
      <label for="name">Ваше имя</label>
    </div>

    <div class="error-row" align="left">
      <label :hidden="isErrorMessageHidden">{{ errorMessageValue }}</label>
    </div>

    <div class="submit-row">
      <input type="submit" v-bind:value="formName === 'login' ? 'Вход' : 'Регистрация'">
    </div>
  </form>
</template>

<script>

import axios from 'axios'

export default {
  name: "loginAndRegistrationForm",
  props: {
    formName: String
  },
  data: function () {
    return {
      password: "",
      email: "",
      phone: "",
      name: "",
      confirmPassword: "",
      isErrorMessageHidden: true,
      errorMessageValue: ""
    }
  },
  watch: {
    formName: function () {
      this.password = "";
      this.email = "";
      this.confirmPassword = "";
      this.isErrorMessageHidden = true;
    }
  },
  methods: {
    validateForm: function (e) {
      /*if (!/^[a-z][a-z\d]*$/i.test(this.login))
        this.errorMessageValue = "Логин может состоять из латинских букв и цифр, но не может начинаться с цифры";
      else */if (!/^[a-z\d]*$/i.test(this.password))
        this.errorMessageValue = "Пароль может состоять из латинских букв и цифр";
      else if (this.formName === "registration" && this.password !== this.confirmPassword)
        this.errorMessageValue = "Пароли не совпадают";
      else {
        this.isErrorMessageHidden = true;
        if(this.formName === "login")
          this.processLogin();
        else this.processRegister();
      }

      this.isErrorMessageHidden = false;
      e.preventDefault();
    },

    processLogin: function () {
      axios.post("api/auth/login", {
        "email": this.email,
        "password": this.password
      })
          .then(response => {
            this.$store.commit("setToken", response.data.token);
            /*axios({
              method: 'get',
              url: "api/user/" + this.login + "/points",
              headers: {'Authorization': 'Bearer ' + response.data.token}
            }).then(response => {
              this.$store.commit("setPoints", response.data);
              this.$store.commit("setLogin", this.login);
              this.$router.push("home");
            })
                .catch(error => {
                  this.errorMessageValue = error.response.data;
                  this.isErrorMessageHidden = false;
                })*/
          })
          .catch(error => {
            this.errorMessageValue = error.response.data;
            this.isErrorMessageHidden = false;
          })
    },

    processRegister: function () {
      axios.post("api/auth/registration", {
        "email": this.email,
        "password": this.password,
        "phone" : this.phone,
        "name" : this.name
      })
          .then(response => {
            this.$store.commit("setToken", response.data.token);
            /*axios({
              method: 'get',
              url: "api/user/" + this.login + "/points",
              headers: {'Authorization': 'Bearer ' + response.data.token}
            }).then(response => {
              this.$store.commit("setPoints", response.data);
              this.$store.commit("setLogin", this.login);
              this.$router.push("home");
            })
                .catch(error => {
                  this.errorMessageValue = error.response.data;
                  this.isErrorMessageHidden = false;
                })*/
          })
          .catch(error => {
            this.errorMessageValue = error.response.data;
            this.isErrorMessageHidden = false;
          })
    },
  }
}
</script>