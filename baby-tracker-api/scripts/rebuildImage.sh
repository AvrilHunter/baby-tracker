#!/bin/bash

helm uninstall tracker

echo "Waiting for tracker pods to terminate..."

while true; do
  pods=$(kubectl get pods -l     app.kubernetes.io/name=baby-tracker-api-chart
 --no-headers 2>/dev/null | wc -l)

  if [ "$pods" -eq 0 ]; then
    echo "All tracker pods terminated"
    break
  fi

  echo "Still waiting... ($pods pods remaining)"
  sleep 2
done

minikube image rm localhost/tracker:latest

podman rmi localhost/tracker:latest

podman build -t tracker:latest .

podman save -o tracker.tar tracker:latest

minikube image load tracker.tar

helm install tracker baby-tracker-api-chart

export POD_NAME=$(kubectl get pods --namespace default -l "app.kubernetes.io/name=baby-tracker-api-chart,app.kubernetes.io/instance=tracker" -o jsonpath="{.items[0].metadata.name}")
export CONTAINER_PORT=$(kubectl get pod --namespace default $POD_NAME -o jsonpath="{.spec.containers[0].ports[0].containerPort}")

while true; do
  STATUS=$(kubectl get pod "$POD_NAME" -o jsonpath='{.status.conditions[?(@.type=="Ready")].status}')

  if [ "$STATUS" = "True" ]; then
    echo "Pod is ready!"
    break
  fi

  echo "Waiting for pod to be ready..."
  sleep 5
done

echo "Visit http://127.0.0.1:8080 to use your application"
kubectl --namespace default port-forward $POD_NAME 8080:$CONTAINER_PORT


