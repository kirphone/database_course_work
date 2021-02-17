import requests
import random

def create_reviews(cursor):
    response = requests.get('https://fish-text.ru/get')
    cursor.execute("SELECT id FROM \"order\" where status = 'Оплачен'")
    list_of_order_id = cursor.fetchall()

    cursor.execute("SELECT order_id FROM review")
    list_of_order_id_used = cursor.fetchall()

    new_id_order = list(filter(lambda x: x not in list_of_order_id_used, list_of_order_id))
    for i in new_id_order:
        response = requests.get('https://fish-text.ru/get').json()
        cursor.execute(
            f"insert into review values(default,{i[0]},'{response['text']}',{random.randint(1, 100)})")
    print(f"Created review : {len(new_id_order)}")
