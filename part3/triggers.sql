/*

    Когда пишется ревью, нужно проверить, что статус ордера корректен

 */
create or replace function Check_Status_Order() returns trigger as
$$
declare
    result boolean;
begin
    result = exists(Select 1 from "order" where "order".id = NEW.order_id and "order".status = 'Оплачен');
    if (result = False) then
        return NEW;
    end if;
    raise exception 'you cannot write a review if the order has not been paid';
end;
$$
    LANGUAGE plpgsql;

Create TRIGGER REVIEW_CHECK_BEFORE_PAY
    BEFORE INSERT
    on review
    for each row
execute PROCEDURE Check_Status_Order();

/*

    Когда статус меняется, то нужно проверить, что курьер указан

 */
create or replace function CHECK_CHANGE_STATUS_ORDER() returns trigger as
$$
begin
    if (NEW.status != 'Поиск курьера' and NEW.courier_id is NULL) then
        raise exception 'you cannot change the status to another, because this status has a courier';
    end if;
    if (OLD.status == 'Поиск курьера' and NEW.status != 'Покупка товаров') then
        raise exception 'you cannot change the status to another like this';
    end if;
    if (OLD.status == 'Покупка товаров' or NEW.status != 'Доставка товаров') then
        raise exception 'you cannot change the status to another like this';
    end if;
    if (OLD.status == 'Доставка товаров' or NEW.status != 'Оплачен') then
        raise exception 'you cannot change the status to another like this';
    end if;
    if (OLD.status == 'Оплачен') then
        raise exception 'you cannot change the status to another like this';
    end if;
    return NEW;
end;
$$
    LANGUAGE plpgsql;

Create TRIGGER ORDER_CHECK_STATUS
    BEFORE UPDATE
    on "order"
    for each row
execute PROCEDURE CHECK_CHANGE_STATUS_ORDER();


/*

    Нужно проверить, что начальное состояние задано корректно для order

*/

create or replace function Check_Started_Status_Order() returns trigger as
$$
begin
    if (New.status != 'Поиск курьер' or (New.status == 'Поиск курьер' and New.courier_id is not null)) then
        raise exception 'you cannot create an order with this status or with a specified courier';
    end if;
    return NEW;
end
$$
    LANGUAGE plpgsql;

Create TRIGGER ORDER_CHECK_STATUS_BEFORE_INSERT
    BEFORe INSERT
    on "order"
    for each row
execute PROCEDURE Check_Started_Status_Order();

/*

    Нужно проверить, что сообщение создается для ордера у которого курьер назначен.

*/

create or replace function CHECK_CORRECT_SENDER() returns trigger as
$$

declare
    result boolean;
begin
    result = exists(select 1
                    from "order" o
                    where new.order_id = o.id
                      and o.courier_id is not null
                      and (New.sender_id = o.courier_id or new.sender_id = o.customer_id));
    if (result = false) then
        raise exception 'you cannot create a message with this status or the sender is incorrect';
    end if;
    return NEW;
end
$$
    LANGUAGE plpgsql;

Create TRIGGER ORDER_CHECK_STATUS_BEFORE_INSERT
    BEFORe INSERT
    on message
    for each row
execute PROCEDURE CHECK_CORRECT_SENDER();


/*

   Пользователь выбирает сеть магазинов и выдает список сетей магазинов с id.

*/

create or replace function select_shop_company_with_id()
    returns table
            (
                shop_company_name varchar(31)
            )
as
$$
begin
    return query select * from shop_company;
end;
$$
    LANGUAGE plpgsql;






