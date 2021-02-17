import requests as req
import os
from dotenv import load_dotenv


load_dotenv()

api_key = os.getenv("GOOGLE_MAP_API_KEY")
api_url = 'https://maps.googleapis.com/maps/api/geocode/json'
address = input()

responce = req.get(api_url, params={'language': 'ru', 'address': address, 'key': api_key}).json()


if(responce['status'] == 'OK'):
    coord = responce['results'][0]['geometry']['location']
    lat = coord['lat']
    lng = coord['lng']
    print(f'INSERT INTO shop VALUES (DEFAULT, NULL, {lat}, {lng}, \'Дикси\');')
