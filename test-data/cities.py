import csv
import random

input_file = "worldcities.csv"
output_file = "../microservices/stalkerlog/src/main/resources/data.sql"

# Declare lists
city_names = []
city_lat = []
city_long = []
country_code = []

count = 0

# Open csv in read-only
with open(input_file, "r") as csv_file:
    reader = csv.reader(csv_file, delimiter=',')

    # For each row in csv
    for row in reader:
        if count != 0:
            city_names.append(row[0])
            city_lat.append(row[2])
            city_long.append(row[3])
            country_code.append(row[5])
            # print("{0}, {1}".format(city_names[count-1], country_code[count-1]))

        count += 1

sql_query = "INSERT INTO city (name, fk_country) VALUES\n"

for i in range(0, count-1):
    name = city_names[i].translate(str.maketrans({"'": "\\'"}))
    sql_query += "('{0}', {1}),\n".format(name, random.randrange(1, 251))

# Remove trailing comma
sql_query = sql_query[:len(sql_query)-2]
sql_query += ";\n"

print(sql_query)

with open(output_file, "a") as writer:
    writer.write("\n\n"+sql_query)

