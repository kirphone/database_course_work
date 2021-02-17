from os.path import join
import names, random, string


def random_email_generator():
    return ''.join(random.choice(string.ascii_letters) for _ in range(7)) + "@gmail.com"

def random_digit_seq(count):
    return ''.join([str(random.randint(0, 9)) for _ in range(count)])

def random_phone_num_generator():
    return '8' + random_digit_seq(10)

def create_accounts(count, cursor):
    for _ in range(1, count + 1):
        cursor.execute(f"INSERT INTO account VALUES (DEFAULT, '{names.get_full_name()}', '{random_phone_num_generator()}', '{random_email_generator()}', '{random_digit_seq(15)}')")