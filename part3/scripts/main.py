import psycopg2, os
from dotenv import load_dotenv
from account_generator import create_accounts
from shop_generator import create_shops
from product_generator import create_products
from message_generator import create_messages
from order_generator import create_orders
from review_generator import create_reviews

load_dotenv()


conn = psycopg2.connect(dbname='studs', user=os.getenv('DB_USER_NAME'), 
                        password=os.getenv('DB_PASSWORD'), host='localhost', port = "4757")
cursor = conn.cursor()
conn.autocommit = True
#create_accounts(200, cursor)
#create_shops(cursor)
#create_products(200, cursor)
create_messages(40, cursor)
create_orders(200, cursor)
#create_reviews(cursor)

cursor.close()
conn.close()