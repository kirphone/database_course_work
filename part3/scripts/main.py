import psycopg2, os
from dotenv import load_dotenv
from account_generator import create_accounts
from shop_generator import create_shops
from product_generator import create_products


load_dotenv()


conn = psycopg2.connect(dbname='studs', user=os.getenv('DB_USER_NAME'), 
                        password=os.getenv('DB_PASSWORD'), host='localhost', port = "4757")
cursor = conn.cursor()
#create_account(200, cursor)
#create_shops(cursor)
create_products(200, cursor)

conn.commit()
cursor.close()
conn.close()