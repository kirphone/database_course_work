import psycopg2
import os
import random

# load_dotenv()

connection = psycopg2.connect(
    dbname='studs',
    user=os.getenv("db_user"),
    password=os.getenv("db_password"),
    host='localhost',
    port="5010"
)
cursor = connection.cursor()

cursor.execute("SELECT id FROM shop")
shops_id = cursor.fetchall()

cursor.execute("SELECT id FROM account")
customers_id = cursor.fetchall()

for i in range(int(input("Введите число создаваемых заказов"))):
    shop_id = random.choice(shops_id)[0]
    customer_id = random.choice(customers_id)[0]
    # Координаты спб Широта: 59°56′19″ с.ш. - Долгота: 30°18′50″ в.д.
    cursor.execute(
        f"select create_order({shop_id},{random.random() + 56},{random.random() + 30},{customer_id})")

connection.commit()
cursor.close()
connection.close()

print("Order created")