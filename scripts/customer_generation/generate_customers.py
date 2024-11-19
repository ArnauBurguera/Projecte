import random
import uuid 

# Predefined names and surnames
names = ["John", "Jane", "Grace", "Tom", "Alice", "Bob", "Eve", "Mike", "Sara", "Sam"]
surnames = ["Doe", "Smith", "Green", "Brown", "Black", "White"]

# Predefined roles
roles = ["USER", "ADMIN", "MODERATOR"]

def generate_random_customer():
    customer_id = str(uuid.uuid4())
    name = f"{random.choice(names)} {random.choice(surnames)}"
    bank_account = ''.join(random.choices('0123456789', k=9))
    role = random.choice(roles)
    return (customer_id, name, bank_account, role)

def generate_customers(n):
    customers = [generate_random_customer() for _ in range(n)]
    return customers

if __name__ == "__main__":
    import sys
    n = int(sys.argv[1]) if len(sys.argv) > 1 else 10
    customers = generate_customers(n)
    for customer in customers:
        print(f"('{customer[0]}', '{customer[1]}', '{customer[2]}', '{customer[3]}'),")