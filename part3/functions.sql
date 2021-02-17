/*

Реализовать функции и процедуры на основе описания бизнес-процессов

*/


/*

   Пользователь регистрируется

*/

create or replace function create_user(
    name_in varchar(63),
    phone_in varchar(31),
    email_in varchar(63),
    password_in varchar(63)
) returns text as
$$
begin
    insert into account(name, phone, email, password) values (name_in, phone_in, email_in, password_in);
    return 'account created';
end
$$
    LANGUAGE plpgsql;

/*

   Пользователь выбирает магазин и ему выдают список товаров из этого магазина.

*/

create or replace function select_product_shop(
    shop_id_in int
)
    returns table
            (
                product_id          int,
                product_name        varchar(63),
                product_description text,
                product_category    varchar(15),
                price               real
            )
as
$$
begin
    if not exists(select * from product_shop where shop_id = shop_id_in) then
        raise exception 'Shop products with this id is not exist';
    end if;
    return query
        select p.id, p.name, p.description, p.category, ps.price
        from product_shop ps
                 join product p on p.id = ps.product_id
        where ps.shop_id = shop_id_in;
end;
$$
    LANGUAGE plpgsql;

/*

   Создаем заказ

*/

create or replace function create_order(
    shop_id_in int,
    address_lat_in double precision,
    address_lng_in double precision,
    customer_id_in int
)
    returns int
as
$$
declare
    order_id int;
begin
    insert into "order"(address_lat, address_lng, shop_id, customer_id, status)
    values (address_lat_in, address_lng_in, shop_id_in, customer_id_in, 'Поиск курьера')
    returning id into order_id;
    return order_id;
end;
$$
    LANGUAGE plpgsql;

/*

   Добавляем в корзину заказанный товар

*/

create or replace function add_order_product(
    product_id_in int,
    count int,
    price_in int,
    need_confirm_in boolean,
    order_id_in int
)
    returns text
as
$$
begin
    insert into order_product(order_id, product_id, price, product_count, need_confirm)
    values (order_id_in, product_id_in, price_in, count, need_confirm_in);
    return 'Added product to order';
end
$$
    LANGUAGE plpgsql;

/*
    Создаем сообщение
*/

create or replace function create_message(
    sender_id_in int,
    order_id_in int,
    message text
)
    returns text
as
$$
begin
    insert into message(message_text, sender_id, order_id, send_datetime)
    values (message, sender_id_in, order_id_in, current_timestamp);
    return 'message created';
end
$$
    LANGUAGE plpgsql;