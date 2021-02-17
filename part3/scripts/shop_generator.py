import requests as req
import os, json
from dotenv import load_dotenv
from urllib.parse import unquote

from requests.models import Response


load_dotenv()
    
api_key = os.getenv("GOOGLE_MAP_API_KEY")
api_url = 'https://maps.googleapis.com/maps/api/geocode/json'


def getCoordByAddress(address):
    responce = req.get(api_url, params={'language': 'ru', 'address': address, 'key': api_key}).json()
    coord = responce['results'][0]['geometry']['location']
    lat = coord['lat']
    lng = coord['lng']
    return (lat, lng)


def getDiksyShops():
    responce = req.get('https://dixy.ru/local/ajax/components/dixy_shop_list.php').text
    responce = unquote(responce)
    responce = json.loads(responce)
    return responce['Санкт-Петербург']['cities']['Санкт-Петербург']['shops']


def create_shop(cursor):
        for shop in getDiksyShops():
            cursor.execute(f"INSERT INTO shop VALUES (DEFAULT, NULL, {shop['latitude']}, {shop['longitude']}, 'Дикси')")
