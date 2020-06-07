Feature: Validating Place APIs 
@AddPlace @Regression
Scenario Outline: Verify if Places is being Successfully added using AddPlaceAPI
Given Add Place Payload with "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "POST" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id created to map "<name>" using "getPlaceAPI"

Examples: 
  |name|language|address          |
  |shan|tamil   |voc nagar lalgudi|
# |Radhi|English|voc nagar authoor|

@DeletePlace @Regression
Scenario: Verify if place is being successfully deleted using deletePlaceAPI
Given DeletePlace Payload
When user calls "deletePlaceAPI" with "POST" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
