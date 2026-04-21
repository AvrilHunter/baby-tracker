

Ticket 1 - initial set up
Ticket 2 - base Java Spring repo structure
2.1 Install bruno
Manually test endpoints
Write tests for existing endpoints

Ticket 3 - base Java front end
Ticket 4 - devOps - create dockerfile
            install minikube
            create helm charts
            instructions how to deploy locally onto minikube
Ticket 6 - update README with how to deploy application - write a script file for these actions
Ticket 7 - create terraform infra for an ECR
Ticket 8 - create github actions to automatically update the image
Ticket 9 - review creating an EKS, or alternatively deploy onto an EC2
Ticket 10 - review spring application improving testing and functionality


# Set Up

## Pre-requisites
Install [podman] (https://podman.io/docs/installation)
Install [helm] (https://helm.sh/docs/intro/install/)
Install [kubectl] (https://kubernetes.io/docs/tasks/tools/)
Install [minikube] (https://minikube.sigs.k8s.io/docs/start/?arch=%2Flinux%2Fx86-64%2Fstable%2Fbinary+download)

## Run Spring Application
To run the spring api locally 
`./mvnw spring-boot:run`

### Containerisation
`cd baby-tracker-api`
Build the image
` podman build -t tracker .` 
Run the program locally
`podman run -d -p 1024:8080 tracker`

## Running on a cluster

`minikube start` if you encounter issues review [documentation] (https://minikube.sigs.k8s.io/docs/drivers/podman/)

