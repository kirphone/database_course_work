INSERT INTO account VALUES (DEFAULT, 'Kirill', '82323828323', 'funemail@mail.ru', '12345');
INSERT INTO account VALUES (DEFAULT, 'Artem', '89313432378', 'artem@mail.ru', '54321');


INSERT INTO status_order VALUES ('Поиск курьера', NULL);
INSERT INTO status_order VALUES ('Покупка товаров', NULL);
INSERT INTO status_order VALUES ('Доставка товаров', NULL);
INSERT INTO status_order VALUES ('Оплачен', NULL);


INSERT INTO shop_company VALUES ('Лента');
INSERT INTO shop_company VALUES ('Магнит');
INSERT INTO shop_company VALUES ('Окей');
INSERT INTO shop_company VALUES ('Дикси');
INSERT INTO shop_company VALUES ('Перекрёсток');


INSERT INTO shop VALUES (DEFAULT, NULL, 59.94504209999999, 30.2657515, 'Дикси');
INSERT INTO shop VALUES (DEFAULT, NULL, 59.9434704, 30.2791995, 'Дикси');
INSERT INTO shop VALUES (DEFAULT, NULL, 59.94284200000001, 30.2827339, 'Дикси');
INSERT INTO shop VALUES (DEFAULT, NULL, 59.9071733, 30.2995099, 'Дикси');
INSERT INTO shop VALUES (DEFAULT, NULL, 59.9786347, 30.3372046, 'Дикси');
INSERT INTO shop VALUES (DEFAULT, NULL, 59.88150590000001, 29.8944949, 'Лента');
INSERT INTO shop VALUES (DEFAULT, NULL, 59.90303899999999, 29.76829, 'Лента');


INSERT INTO product_category VALUES ('Молочные');
INSERT INTO product_category VALUES ('Мясо');
INSERT INTO product_category VALUES ('Вода');
INSERT INTO product_category VALUES ('Алкоголь');
INSERT INTO product_category VALUES ('Бакалея');
INSERT INTO product_category VALUES ('Фрукты');


INSERT INTO product VALUES (DEFAULT, 'Фарш САМСОН говяжий рубленый кат.А охл.жирн.7.5%, Россия, 400 г', NULL, 'Мясо');
INSERT INTO product VALUES (DEFAULT, 'Изделия макаронные BIOITALIA спиральки c томатом и шпинатом, Италия, 500 г', NULL, 'Бакалея');
INSERT INTO product VALUES (DEFAULT, 'Томат коктейль желтый, 250 г', NULL, 'Фрукты');
INSERT INTO product VALUES (DEFAULT, 'Вода минеральная Нарзан, 1 л', NULL, 'Вода');


INSERT INTO product_shop VALUES (1, 6, 199);
INSERT INTO product_shop VALUES (2, 6, 240);
INSERT INTO product_shop VALUES (1, 7, 199);
INSERT INTO product_shop VALUES (3, 1, 50);
INSERT INTO product_shop VALUES (3, 2, 50);
INSERT INTO product_shop VALUES (3, 3, 50);
INSERT INTO product_shop VALUES (3, 4, 50);
INSERT INTO product_shop VALUES (3, 5, 50);
INSERT INTO product_shop VALUES (4, 3, 199);


INSERT INTO "order" VALUES (DEFAULT, 60.0347421, 30.2396958, 6, 2, NULL, 'Поиск курьера');
INSERT INTO order_product VALUES (1, 1, 199, 1, false);
INSERT INTO order_product VALUES (1, 2, 240, 1, false);
UPDATE "order" SET courier_id = 1, status = 'Покупка товаров' WHERE customer_id = 2;


INSERT INTO message VALUES (DEFAULT, 'Привет, тебе долго до магазина идти?', 2, 1, now());
INSERT INTO message VALUES (DEFAULT, 'Минут 5', 1, 1, now());


INSERT INTO discount_card VALUES (DEFAULT, 'Премиум скидочная карта', NULL, 'Лента', 2);