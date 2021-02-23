import axios from "axios";
import store from "@/store";

export default axios.create({
    baseURL: 'api/',
    headers: {
        Authorization: "Bearer " + store.state.token
    }
});