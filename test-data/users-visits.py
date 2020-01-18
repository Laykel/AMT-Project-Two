from random_username.generate import generate_username
from bcrypt import *
from random import randint

user_output_file = "../microservices/stalkerlog/src/main/resources/db-scripts/users.sql"

visit_output_file = "visits.csv"
nbr_of_users = 1000

# Insert random number of visits
def generate_visits(username):
    query = ""

    nbr_of_visits = randint(0, 10000)
    for i in range(0, nbr_of_visits):
        year = randint(1880, 2019)
        month = randint(1, 12)
        day = randint(1, 28)
        date = "{:04}-{:02}-{:02}".format(year, month, day)
        city = randint(1, 12900)

        query +=  "0,{0},{1},{2},{2}\n".format(username, city, date)

    return query

# Create sql query and add base users
user_query = "INSERT INTO User (username, passwordHash) VALUES\n"
user_query += "('luc', '$2a$10$XEdw2VBLYtp72kXQ4tAXku3cVQLsg/06BdlXIy8oL90yueemZ5ZJu'),\n"
user_query += "('alison', '$2a$10$z8UTnoTbpyWraSPZ/yBeEu6Ldn728C9Eml3KbPE2.9k8ATbq0g9ii'),\n"
user_query += "('admin', '$2a$10$2RdXQQaOgXjrm4U7MpJeIuD7MGtNxAJhNLNoaphToDhSt4XqxVqbq'),\n"

# Generate usernames
nameList = generate_username(nbr_of_users)

# Make sure every username is unique
names = list(dict.fromkeys(nameList))

# Create queries for visits at the same time
visit_load = "idVisit,fk_username,fk_idCity,startDate,endDate\n"

visit_load += generate_visits("luc")
visit_load += generate_visits("alison")

# Add names to sql query
for name in names:
    pwd = "password"
    hashed = hashpw(pwd.encode('utf-8'), gensalt())
    user_query += "('{0}', '{1}'),\n".format(name, hashed.decode('utf-8'))

    # Insert random number of visits
    visit_load += generate_visits(name)

# Remove trailing comma
user_query = user_query[:len(user_query)-2]
user_query += ";\n"

# Write to files
with open(user_output_file, "w+") as writer:
    writer.write(user_query)

with open(visit_output_file, "w+") as writer:
    writer.write(visit_load)

