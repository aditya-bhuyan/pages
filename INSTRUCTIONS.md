# Dockerizing the application
## Image Creation
- docker build -t <docker.user.name>/<repo.name>:<tag.name> .
- docker run -p 8080:8080 -t <docker.user.name>/<repo.name>:<tag.name>
- Open the application in localhost:8080 and test the application

## Pushing the application to Docker repo
- docker push <docker.user.name>/<repo.name>:<tag.name>

# Running the application on Kubernetes cluster
- Replace the value for **image** in **pages-deployment.yaml** with "<docker.user.name>/<repo.name>:<tag.name>"
Execute the below two commands to create a deployment and expose it as a service
- kubectl -f deployment/pages-service.yaml
- kubectl -f deployment/pages-deployment.yaml

 