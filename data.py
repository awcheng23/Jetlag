import requests

params = {
  'access_key': 'd5890c79bb6123fa6f309e7c6dd5bae0'
}

api_result = requests.get('https://api.aviationstack.com/v1/flights', params)

api_response = api_result.json()

for flight in api_response['results']:
    if (flight['live']['is_ground'] is False):
        print(u'%s flight %s from %s (%s) to %s (%s) is in the air.' % (
            flight['airline']['name'],
            flight['flight']['iata'],
            flight['departure']['airport'],
            flight['departure']['iata'],
            flight['arrival']['airport'],
            flight['arrival']['iata']))
