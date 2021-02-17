import requests
import psycopg2
import os
import random

response = requests.get('https://fish-text.ru/get')

# load_dotenv()


connection = psycopg2.connect(
    dbname='studs',
    user=os.getenv("db_user"),
    password=os.getenv("db_password"),
    host='localhost',
    port="5010"
)
cursor = connection.cursor()
cursor.execute("SELECT id FROM \"order\" where status = \'Оплачен\'")

list_of_order_id = cursor.fetchall()

cursor.execute("SELECT order_id FROM review")
list_of_order_id_used = cursor.fetchall()

new_id_order = [i for i in list_of_order_id if i not in list_of_order_id_used]
c = 0
for i in new_id_order:
    c+=1
    response = requests.get('https://fish-text.ru/get').json()
    cursor.execute(
        f"insert into review values(default,{i[0]},'{response['text']}',{random.randint(1, 100)})")

connection.commit()
cursor.close()
connection.close()

print(f"Created review : {c}")
