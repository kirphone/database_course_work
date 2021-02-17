/*
	
   Когда пишется ревью, нужно проверить, что статус ордера корректен

*/
create or replace function Check_Status_Order() returns trigger as
$$
declare
result boolean;
begin
   result = exists(Select 1 from "order" where "order".id = NEW.order_id and "order".status = 'Оплачен');
   if (result = True) then
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
   if (OLD.status = 'Поиск курьера' and NEW.status != 'Покупка товаров') then
       raise exception 'you cannot change the status to another like this';
end if;
   if (OLD.status = 'Покупка товаров' and NEW.status != 'Доставка товаров') then
       raise exception 'you cannot change the status to another like this';
end if;
   if (OLD.status = 'Доставка товаров' and NEW.status != 'Оплачен') then
       raise exception 'you cannot change the status to another like this';
end if;
   if (OLD.status = 'Оплачен') then
       raise exception 'you cannot change this status to another like this';
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
   if (New.status != 'Поиск курьер' or New.courier_id is not null) then
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

begin
   /*
       Ищем тот заказ, где курьер назначен и sender_id совпадает, если нету,
       то значит нельзя создать сообщение с таким order_id
   */
   if( exists(select 1
                   from "order" o
                   where new.order_id = o.id
                     and o.courier_id is not null
                     and (New.sender_id = o.courier_id or new.sender_id = o.customer_id)) = false) then
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
