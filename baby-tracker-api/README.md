./mvnw spring-boot:run


Ticket 1 - initial set up
Ticket 2 - base Java Spring repo structure
2.1 Install bruno
Manually test endpoints
Write tests for existing endpoints

Ticket 3 - base Java front end
Ticket 4 - start devOps - create dockerfile
Ticket 5 - install minikube
            create helm charts
            instructions how to deploy locally onto minikube
Ticket 6 - update README with how to deploy application - write a script file for these actions
Ticket 7 - create terraform infra for an ECR
Ticket 8 - create github actions to automatically update the image
Ticket 9 - review creating an EKS, or alternatively deploy onto an EC2
Ticket 10 - review spring application improving testing and functionality


# How to run locally
## Pre-requisites
Install podman
`cd baby-tracker-api`
## To run locally
Build the image
` podman build -t tracker .` 
Run the program locally
`podman run -d -p 1024:8080 tracker`