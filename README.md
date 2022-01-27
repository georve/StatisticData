# StatisticData
Spring-boot-project generated to make crud operation about Vehicle Miles Traveled during covid-19
This project performs crud operation over these registers

This project is based on docker compose but it is neeeds
to fill several environment variables defines as
examples

MYSQLDB_USER=crm_user
MYSQLDB_ROOT_PASSWORD=123456
MYSQLDB_DATABASE=crmdb
MYSQLDB_PASSWORD=crmPassword
MYSQLDB_LOCAL_PORT=3307
MYSQLDB_DOCKER_PORT=3306

SPRING_LOCAL_PORT=6868
SPRING_DOCKER_PORT=8080
DOCKER_DEBUG_PORT=5005

these value can be change and stored in a .env file

1. to Run this project you must run this script in the project
path  $docker-compose up --build and this will create two containers
one for mysql and another for the backend
2. The rest service is provides in the port SPRING_LOCAL_PORT=6868
