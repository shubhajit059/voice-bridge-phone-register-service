# README #

#### Run the following command:

```bash 
sh stop_api.sh cmd test
sh stop_api.sh query test

sh start_api.sh cmd test
sh start_api.sh query test

docker-compose -f docker-compose.yml -f docker-compose-test.yml --profile cmd up --build
docker-compose -f docker-compose.yml -f docker-compose-local.yml --profile query up --build

docker exec -it 84727cc3eb5d bash -c "java -jar /axonserver/axonserver-cli.jar register-application -a call-command-service -r DISPATCH_COMMANDS,DISPATCH_QUERY,PUBLISH_EVENTS,READ_EVENTS,SUBSCRIBE_COMMAND_HANDLER,SUBSCRIBE_QUERY_HANDLER,READ,WRITE -t \$(cat /axonserver/config/axonserver.token)"
docker exec -it 84727cc3eb5d bash -c "java -jar /axonserver/axonserver-cli.jar register-application -a call-query-service -r DISPATCH_COMMANDS,DISPATCH_QUERY,PUBLISH_EVENTS,READ_EVENTS,SUBSCRIBE_COMMAND_HANDLER,SUBSCRIBE_QUERY_HANDLER,READ,WRITE -t \$(cat /axonserver/config/axonserver.token)"
```