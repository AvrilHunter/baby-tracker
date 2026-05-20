# Instructions

## Prerequisites
1. Install [podman](https://podman.io/docs/installation)
2. Install [helm](https://helm.sh/docs/intro/install/)
3. Install [kubectl](https://kubernetes.io/docs/tasks/tools/)
4. Install [minikube](https://minikube.sigs.k8s.io/docs/start/?arch=%2Flinux%2Fx86-64%2Fstable%2Fbinary+download)
5. Optional [k9s](https://k9scli.io/topics/install/)

## Run Spring Application

To run the spring api locally 

`./mvnw spring-boot:run`

## Containerisation

`cd baby-tracker-api`

Build the image

` podman build -t tracker .` 

Run the application locally

`podman run -d -p 1024:8080 tracker`

## Run on Local Cluster

`minikube start` 

if you encounter issues review [documentation](https://minikube.sigs.k8s.io/docs/drivers/podman/)

`podman save -o tracker.tar localhost/tracker:latest`

`minikube image load tracker.tar`

`helm install tracker baby-tracker-api-chart`

## Port forward

```
export POD_NAME=$(kubectl get pods --namespace default -l "app.kubernetes.io/name=baby-tracker-api-chart,app.kubernetes.io/instance=tracker" -o jsonpath="{.items[0].metadata.name}")
export CONTAINER_PORT=$(kubectl get pod --namespace default $POD_NAME -o jsonpath="{.spec.containers[0].ports[0].containerPort}")
echo "Visit http://127.0.0.1:8080 to use your application"
kubectl --namespace default port-forward $POD_NAME 8080:$CONTAINER_PORT
```