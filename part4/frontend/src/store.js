import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";


const store = createStore({
    state: {
        token : "",
        tokenDate : null,
        isClient : true,
        order : {
            shopCompanyName : ""
        }
    },
    mutations: {
        setToken(state, tokenString) {
            state.token = tokenString;
            state.tokenDate = new Date().getTime() + 3600000;
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