#!/bin/bash

echo "Uninstalling helm chart for tracker"
helm uninstall tracker

echo "Installing helm chart for tracker"
helm install tracker baby-tracker-api-chart

echo "Port forwarding..."
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

kubectl --namespace default port-forward $POD_NAME 8080:$CONTAINER_PORT
echo "Port forward complete - visit http://127.0.0.1:8080 to use your application"

