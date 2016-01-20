# Vehicle Enquiry API

This is a Dropwizard project which sets up an API on port 8050. It takes:

- the Vehicle registration number (vrn) 
- and Vehicle make (make) 

and returns tax and MOT information in JSON format.

### Creating a Docker image

A Docker image was created to hold the Jar file and it's configuration files. I have uploaded this to my [Docker Hub](https://hub.docker.com/) repo.

To create a Docker image of the API (on *Windows 10*) you:

- Install [Docker Toolbox](https://www.docker.com/docker-toolbox)
- Copy the executional .jar file, the .yml file and the Dockerfile into the Docker folder.
- Open *Docker Quickstart Terminal*
- Navigate to the docker folder
- Type ``` docker build .```
- Type ``` docker images ``` to find the image you just created. Copy the imageID.
- Type ``` docker tag imageID DockerhubUsername/ImageName ```
- Type ``` docker login https://index.docker.io/v1/ ``` and login with your credentials.
- type ``` docker push DockerhubUsername/ImageName ```

Your API is now in your Docker hub repo.

## Using Amazon's ECS

Amazon ECS uses Docker images in task definitions to launch containers on EC2 instances. 

This can be done by:

- creating a task definition
- specifying a container name
- referencing the created image URL in the Docker Hub repo
- and adding the required port mapping (8050:8050)

You may also need to change the rules of the security group the EC2 instance is using to allow 8050 inbound calls.

## How to use the API

The Api can be queried by entering:

http://54.213.90.11:8050/vehicles?vrm=VEHICLE_REG&make=VEHICLE_MAKE

*VEHICLE_REG is the Vehicle registration number parameter.*


*VEHICLE_MAKE is the parameter containing the make of the vehicle.*

The IP address (54.213.90.11) is the address of the EC2 instance holding the Docker container.