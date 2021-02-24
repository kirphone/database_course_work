import {createRouter, createWebHistory} from "vue-router";
import LoginAndRegistration from "./components/auth/LoginAndRegistration";
import Home from "./components/home/Home";
import NotFoundError from "./components/NotFoundError";
import ChooseRole from "./components/role/ChooseRole";
import ShopCompaniesList from "@/components/shop/ShopCompaniesList";
import store from "./store";
import Basket from "@/components/basket/Basket";
import Order from "@/components/Order";
import CourierMap from "@/components/CourierMap";
import OrderStatus from "@/components/OrderStatus";

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
                } else
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
            path: '/courier',
            name: "courier",
            component: CourierMap,
            beforeEnter: (to, from, next) => {
                if (hasToken())
                    next();
                else
                    next("/auth");
            }
        },
        {
            path: '/order-get',
            name: "order-get",
            component: OrderStatus,
            beforeEnter: (to, from, next) => {
                if (hasToken())
                    next();
                else
                    next("/auth");
            }
        },
        {
            path: '/choose-role',
            name: "ChooseRole",
            component: ChooseRole,
            beforeEnter: (to, from, next) => {
                if (hasToken())
                    next();
                else
                    next("/auth");
            }
        },
        {
            path: '/basket',
            component: Basket,
            props: true,
            beforeEnter: (to, from, next) => {
                if (hasToken())
                    next();
                else
                    next("/auth");
            }
        },
        {
            path: '/orders',
            component: Order,
            beforeEnter: (to, from, next) => {
                if (hasToken())
                    next();
                else
                    next("/auth");
            }
        },
        {
            path: "/shop-company",
            name: "ShopCompaniesList",
            component: ShopCompaniesList,
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
        },
    ]
});

function hasToken() {
    return (store.state.token !== "") && (store.state.tokenDate > new Date().getTime())
}

export default router