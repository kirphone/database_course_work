import requests as req
from requests.models import MissingSchema
from random import randint

links = {'Молочные' : 'https://5ka.ru/api/v2/special_offers/?categories=698&ordering=&page=1&records_per_page=20&search=&store=',
        'Бакалея' : 'https://5ka.ru/api/v2/special_offers/?categories=870&ordering=&page=1&price_promo__gte=&price_promo__lte=&records_per_page=20&search=&store=',
        'Мясо и рыба' : 'https://5ka.ru/api/v2/special_offers/?categories=716&ordering=&page=1&price_promo__gte=&price_promo__lte=&records_per_page=20&search=&store=',
        'Фрукты и овощи' : 'https://5ka.ru/api/v2/special_offers/?store=&records_per_page=12&page=1&categories=827&ordering=&price_promo__gte=&price_promo__lte=&search='}
shop_count = 200

def create_products(count, cursor):
    res = {'Молочные' : [], 'Бакалея' : [], 'Мясо и рыба' : [], 'Фрукты и овощи' : []}
    for link in links:
        try:
            linkValue = links[link]
            for _ in range(count // 20):
                responce = req.get(linkValue).json()
                linkValue = responce['next']
                res[link].extend([(pr['name'], pr['current_prices']['price_reg__min']) for pr in responce['results']])
        except MissingSchema:
            continue
    for type in res:
        for pr in res[type]:
            product_name, price = pr;
            cursor.execute(f"INSERT INTO product VALUES (DEFAULT, '{product_name}', NULL, '{type}') RETURNING id")
            product_id = cursor.fetchone()[0]
            cursor.execute(f"INSERT INTO product_shop VALUES ({product_id}, {randint(1, 200)}, {price})")
    sum_count = sum([len(res[i]) for i in res])
    print(f"Добалено {sum_count} продуктов")
        



