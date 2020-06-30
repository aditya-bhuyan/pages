# Follow Instructions to dockerize and Kubernetize the Pages Application

## Dockerization
- Add the following lines in Dockerfile
```shell script
  FROM adoptopenjdk:11-jre-openj9
  ARG JAR_FILE=build/libs/*.jar
  COPY ${JAR_FILE} app.jar
  ENTRYPOINT ["java","-jar","/app.jar"]
```
- run the following commands to generate the Docker image
```shell script
docker build -t <docker_username>/<docker_repo>:<tag> .
``` 
- run the following command to run the image
```shell script
docker run -p 8080:8080 -t <docker_username>/<docker_repo>:<tag>
```
Then open the application at http://localhost:8080 to test it.

- Push the image to docker
```shell script
docker push <docker_username>/<docker_repo>:<tag>
```

## Running the image in Kubernetes
- Fill the pages-services.yaml
  * Assign 80 for targetPort and Port
  * Assign TCP for protocol
  * Assign pages for app, servicefor and name
- Fill the pages-deployment.yaml
  * Assign pages for app, servicefor and name
  * Assign <docker_username>/<docker_repo>:tag for image
- Run the following commands in kubernetes to run the application 
```shell script
kubectl -f deployment/pages-service.yaml
kubectl -f deployment/pages-deployment.yaml
```
 