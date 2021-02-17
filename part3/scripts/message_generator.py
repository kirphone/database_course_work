import requests
import random

def create_messages(count, cursor):
    cursor.execute('SELECT customer_id, courier_id, id FROM "order" where courier_id is not null')
    senders = cursor.fetchall()
    for i in range(count):
        response = requests.get('https://fish-text.ru/get').json()
        pair = random.choice(senders)
        cursor.execute(
            f"select create_message({random.choice(pair[:2])},{pair[2]},'{response['text']}')")