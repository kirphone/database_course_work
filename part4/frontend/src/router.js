import {createRouter, createWebHistory} from "vue-router";
import LoginAndRegistration from "./components/auth/LoginAndRegistration";
import Home from "./components/home/Home";
import NotFoundError from "./components/NotFoundError";
import store from "./store";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/auth",
            name: "auth",
            component: LoginAndRegistration,
            beforeEnter: (to, from, next) => {
                if (hasToken()) {
                    next("/home");
                }
                else
                    next();
            }
        },
        {
            path: '/home',
            name: "home",
            component: Home,
            beforeEnter: (to, from, next) => {
                if (hasToken())
                    next();
                else
                    next("/auth");
            }
        },
        {
            path: "/",
            beforeEnter: (to, from, next) => {
                if (hasToken())
                    next("/home");
                else
                    next("/auth");
            }
        },
        {
            path: "/*",
            component: NotFoundError
        }
    ]
});

function hasToken(){
    return (store.state.token !== "") && (store.state.tokenDate > new Date().getTime());
}

export default router