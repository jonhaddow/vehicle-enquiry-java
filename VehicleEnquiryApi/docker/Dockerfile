# Dockerfile for use by spotify/docker-maven-plugin
FROM java:8

MAINTAINER Jon Haddow <haddowjon@gmail.com>

EXPOSE 8050
EXPOSE 8051

ADD /VehicleEnquiry.jar //
ADD /VehicleEnquiry.yml //

CMD java -jar VehicleEnquiry.jar server VehicleEnquiry.yml