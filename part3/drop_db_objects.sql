drop table if exists "order",
    discount_card,
    shop,
    product,
    product_category,
    shop_company,
    status_order,
    account,
    product_shop,
    message,
    review,
    order_product;

drop function if exists Check_Status_Order();
drop function if exists CHECK_CHANGE_STATUS_ORDER();
drop function if exists Check_Started_Status_Order();
drop function if exists CHECK_CORRECT_SENDER();
drop function if exists Create_User(
    name_in varchar(63),
    phone_in varchar(31),
    email_in varchar(63),
    password_in varchar(63)
    );
drop function if exists select_product_shop(
    shop_id_in int
);
drop function if exists create_order(
    shop_id_in int,
    address_lat_in double precision,
    address_lng_in double precision,
    customer_id_in int
);
drop function if exists add_order_product(
    product_id_in int,
    count int,
    price_in int,
    need_confirm_in boolean,
    order_id_in int
);
drop function if exists create_message(
    sender_id_in int,
    order_id_in int,
    message text
);