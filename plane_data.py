import requests
import json

params = {
  'access_key': 'd5890c79bb6123fa6f309e7c6dd5bae0'
}

api_result = requests.get('https://api.aviationstack.com/v1/flights', params)

api_response = api_result.json()

# for flight in api_response['results']:
#     if (flight['live']['is_ground'] is False):
#         print(u'%s flight %s from %s (%s) to %s (%s) is in the air.' % (
#             flight['airline']['name'],
#             flight['flight']['iata'],
#             flight['departure']['airport'],
#             flight['departure']['iata'],
#             flight['arrival']['airport'],
#             flight['arrival']['iata']))


# Example JSON file: data.json
# {"name": "John", "age": 30, "city": "New York"}

# Load JSON data from a file
#data = json.load(api_response)

# Display the parsed data
print(api_response)


#print(api_response)