import requests
import json

apiKey = input("API Key: ")
apiUrl = "https://aeroapi.flightaware.com/aeroapi/"

airport = 'KRDU'
payload = {'max_pages': 10}
auth_header = {'x-apikey':apiKey}

response = requests.get(apiUrl + f"airports/{airport}/flights",
    params=payload, headers=auth_header)

if response.status_code == 200:
    json_content = response.json()
    file_name = 'flight_data.json'

    # Save the JSON content to the file
    with open(file_name, 'w') as json_file:
        json.dump(json_content, json_file, indent=2)
else:
    print("Error executing request")


# json_content = response.json()
# flights = json_content.get('flights', [])
# print("List of flights:")
# for flight in flights:
#     print(flight)
        