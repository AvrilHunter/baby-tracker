

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
            update README with how to deploy application - write a script file for these actions

Ticket 7 - create terraform infra for an ECR
Ticket 8 - create github actions to automatically update the image
Ticket 9 - review creating an EKS, or alternatively deploy onto an EC2
Ticket 10 - review spring application improving testing and functionality
Ticket 11 - binding volumes to the containers


# Set Up

## Pre-requisites
Install [podman] (https://podman.io/docs/installation)
Install [helm] (https://helm.sh/docs/intro/install/)
Install [kubectl] (https://kubernetes.io/docs/tasks/tools/)
Install [minikube] (https://minikube.sigs.k8s.io/docs/start/?arch=%2Flinux%2Fx86-64%2Fstable%2Fbinary+download)
Optional [k9s] (https://k9scli.io/topics/install/)

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

`podman save -o tracker.tar localhost/tracker:latest`

`minikube image load tracker.tar`

`helm install tracker baby-tracker-api-chart`

# Port forwarding

```
export POD_NAME=$(kubectl get pods --namespace default -l "app.kubernetes.io/name=baby-tracker-api-chart,app.kubernetes.io/instance=tracker" -o jsonpath="{.items[0].metadata.name}")
export CONTAINER_PORT=$(kubectl get pod --namespace default $POD_NAME -o jsonpath="{.spec.containers[0].ports[0].containerPort}")
echo "Visit http://127.0.0.1:8080 to use your application"
kubectl --namespace default port-forward $POD_NAME 8080:$CONTAINER_PORT
```