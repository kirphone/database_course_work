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