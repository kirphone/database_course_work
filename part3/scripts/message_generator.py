import requests
import psycopg2
import os
import random

from sqlite3 import OperationalError

response = requests.get('https://fish-text.ru/get')

n = int(input("Введите число сообщений"))
# load_dotenv()


connection = psycopg2.connect(
    dbname='studs',
    user=os.getenv("db_user"),
    password=os.getenv("db_password"),
    host='localhost',
    port="5010"
)
cursor = connection.cursor()
cursor.execute("SELECT customer_id, courier_id,id FROM \"order\" where courier_id is not null")

senders = cursor.fetchall()

for i in range(n):
    response = requests.get('https://fish-text.ru/get').json()
    pair = random.choice(senders)

    cursor.execute(
        f"select create_message({random.choice(pair[:2])},{pair[2]},'{response['text']}')")

connection.commit()
cursor.close()
connection.close()