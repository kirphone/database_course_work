import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";


const store = createStore({
    state: {
        orderId : 1,
        shopId : "3",
        userId : "",
        token : "",
        tokenDate : null,
        isClient : true,
        order : {
            shopCompanyName : ""
        }
    },
    mutations: {
        setOrderId(state, orderId){
            console.log(orderId)
            state.orderId = orderId
        },
        setShopId(state, shopId){
            state.shopId = shopId
        },
        setToken(state, tokenString) {
            state.token = tokenString;
            state.tokenDate = new Date().getTime() + 3600000;
        },
        setUserId(state, userId) {
            state.userId = userId;
        },
        removeToken(state){
          state.token = "";
          state.tokenDate = 0;
        },
        isClient(state, boolStatus){
            //boolStatus - true = Client , false = Courier
            state.isClient = boolStatus
        },
        selectShop(state, shopCompanyName){
            state.shopCompanyName = shopCompanyName
        }
    },
    plugins: [createPersistedState()],
    devtools: true
});

export default store