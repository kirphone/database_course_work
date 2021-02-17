import random


def create_orders(count, cursor):
    cursor.execute("SELECT id FROM shop")
    shops_id = cursor.fetchall()

    cursor.execute("SELECT id FROM account")
    customers_id = cursor.fetchall()

    for _ in range(count):
        shop_id = random.choice(shops_id)[0]
        customer_id = random.choice(customers_id)[0]
        # Координаты спб Широта: 59°56′19″ с.ш. - Долгота: 30°18′50″ в.д.
        cursor.execute(
            f"select create_order({shop_id},{random.random() + 59},{random.random() + 30},{customer_id})")