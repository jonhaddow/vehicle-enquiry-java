# Vehicle Enquiry API

This is a project experimenting different technologies and services available to create an Vehicle Enquiry API. I'm using the [Dropwizard](http://www.dropwizard.io/0.9.2/docs/) framework on Java. 

The API is exposed on port 8050 and accessed by this URL:

http://HOST:8050/vehicles?vrn=VEHICLE_REG&make=VEHICLE_MAKE

Two parameters are passed in to the URL: 
- The vehicle registration number
- The vehicle make


## Hosting on AWS

### Creating a Docker image

A Docker image was created to hold the Jar file and it's configuration files. 
I have uploaded this to my [Docker Hub](https://hub.docker.com/) repo.

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

### Using Amazon's ECS

*This is not included in free tier.*

Amazon ECS uses Docker images in task definitions to launch containers on EC2 instances. 

This can be done by:

- creating a task definition
- specifying a container name
- referencing the created image URL in the Docker Hub repo
- and adding the required port mapping (8050:8050)

You may also need to change the rules of the security group the EC2 instance is using to allow 8050 inbound calls.