## Instructions for the logging and monitoring

### Updating the log-pv.yaml and log-pvc.yaml

- For log-pv.yaml fill all the section
  * Assign name as log-persistent-volume under metadata section
  * Assign volumeMode to FileSystem under spec
  * Assign storageClassName to slow under spec
  * Capacity storage would be 500Mi
  * accessModes would be ReadWriteOnce
  * hostPath would be "/mnt/logs"
- For log-pvc.yaml fill all the section
  * name would be log-persistent-claim
  * volumeMode would be  FileSystem under spec
  * storageClassName would be slow under spec
  * resources/requests/storage would be 500Mi
  * accessModes would be ReadWriteOnce
- Add log level and and log file name in application.properties
  * logging.file.name=/var/tmp/pages-app.log
  * debug=true
  * logging.level.org.springframework.web=debug
- Code change in HomeController
  * Add a Logger from slf4j api
  * Add  debug,warn,trace,info and error messages in getPage() method
  
- Add logback.xml under resources folder with basic logging configuration for FILE and STDOUT appender
  ```xml
  <?xml version = "1.0" encoding = "UTF-8"?>
  <configuration>
      <include resource="org/springframework/boot/logging/logback/base.xml"/>
      <logger name="org.springframework.web" level="DEBUG"/>
      <appender name = "STDOUT" class = "ch.qos.logback.core.ConsoleAppender">
          <encoder>
              <pattern>[%d{yyyy-MM-dd'T'HH:mm:ss.sss'Z'}] [%C] [%t] [%L] [%-5p] %m%n</pattern>
          </encoder>
      </appender>
  
      <appender name = "FILE" class = "ch.qos.logback.core.FileAppender">
          <File>/var/tmp/pages-app.log</File>
          <encoder>
              <pattern>[%d{yyyy-MM-dd'T'HH:mm:ss.sss'Z'}] [%C] [%t] [%L] [%-5p] %m%n</pattern>
          </encoder>
      </appender>
  
      <root level = "DEBUG">
          <appender-ref ref = "FILE"/>
          <appender-ref ref = "STDOUT"/>
      </root>
  </configuration>
  ```
- Add log volume details in pages-deployment.yaml
  
- Add liveness and readiness probe information in pages-deployment.yaml
 ```yaml
    readinessProbe:
     tcpSocket:
       port: 8080
     initialDelaySeconds: 150
    livenessProbe:
     httpGet:
       path: /actuator/health
       port: 8080
     initialDelaySeconds: 150
```
- Build the application with 
```sh
./gradlew clean build
```
- Docker build and push the application with tag **logging**
- Change the tag to *logging* in pages-deployment.yaml
- Use the following commands to deploy the application in kubernetes
```shell script
kubectl apply -f deployment/log-pv.yaml
kubectl apply -f deployment/log-pvc.yaml
kubectl apply -f deployment/pages-config.yaml
kubectl apply -f deployment/pages-service.yaml
kubectl apply -f deployment/pages-deployment.yaml
```

- The application would be ready after 150 seconds as the readiness probe would start after 150 seconds
- Keep on checking the status of the pod which is part of the pages deployment
 