import csv
import json
import requests

input_file = "worldcities.csv"

# API variables
api_url_base = 'https://restcountries.eu/rest/v2/'
headers = {'Content-Type': 'application/json'}


# Get data from API
def get_codes_from_countries():
    codes = set()

    api_url = '{0}all'.format(api_url_base)

    response = requests.get(api_url, headers=headers)

    if response.status_code == 200:
        countries = json.loads(response.content.decode('utf-8'))

        for country in countries:
            codes.add(country['alpha2Code'])

        return codes
    else:
        return None


# Get data from CSV
def get_codes_from_cities():
    codes = set()

    count = 0
    # Open csv in read-only
    with open(input_file, "r") as csv_file:
        reader = csv.reader(csv_file, delimiter=',')

        # For each row in csv
        for row in reader:
            if count != 0:
                codes.add(row[5])

            count += 1

    return codes


country_code_csv = get_codes_from_cities()
all_country_codes = get_codes_from_countries()

print(country_code_csv.difference(all_country_codes))

