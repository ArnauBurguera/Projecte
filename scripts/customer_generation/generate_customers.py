import random
import uuid

# Predefined names and surnames
names = ["Frodo", "Gandalf", "Aragorn", "Legolas", "Gimli",
         "Samwise", "Boromir", "Pippin", "Merry", "Saruman",
         "Elrond", "Galadriel", "Eowyn", "Theoden", "Sauron",
         "Fëanor", "Fingolfin", "Túrin", "Lúthien", "Melian"]

surnames = ["Baggins", "Brandybuck", "Took", "Gamgee", "Greenleaf",
            "Greyhame", "Shieldsheaf", "Bloodeye", "Stout", "Ironfoot",
            "Stormbreaker", "Galadhrim", "Evenstar", "Bëor", "Finarfin",
            "Noldor", "Mithrandir", "Mirkwood", "Rivendell", "Gríma"]

# Predefined roles
roles = ["USER"]


def generate_random_customer():
    customer_id = str(uuid.uuid4())
    name = f"{random.choice(names)} {random.choice(surnames)}"
    bank_account = ''.join(random.choices('0123456789', k=9))
    role = random.choice(roles)
    return customer_id, name, bank_account, role


def generate_customers(number_of_customers):
    generated_customers = [generate_random_customer() for _ in range(number_of_customers)]
    return generated_customers


if __name__ == "__main__":
    import sys

    n = int(sys.argv[1]) if len(sys.argv) > 1 else 10
    customers = generate_customers(n)
    for customer in customers:
        print(f"('{customer[0]}', '{customer[1]}', '{customer[2]}', '{customer[3]}'),")