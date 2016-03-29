# Vehicle Enquiry API

This is a Dropwizard project which sets up an API on port 8050. It takes:

- the Vehicle registration number (vrn) 
- and Vehicle make (make) 

and returns tax and MOT information in JSON format.

## How to use the API

The Api can be queried by entering:

http://PUBLICIP:8050/vehicles?vrm=VEHICLE_REG&make=VEHICLE_MAKE

*VEHICLE_REG is the Vehicle registration number parameter.*


*VEHICLE_MAKE is the parameter containing the make of the vehicle.*

*The IP address is the address of the EC2 instance holding the Docker container.*
