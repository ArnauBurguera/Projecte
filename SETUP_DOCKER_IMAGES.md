Docker commands
### Microsoft SQL Server ###

https://hub.docker.com/_/microsoft-mssql-server

Windows PC:
docker run --name sqlserver2019 -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=Adam123456" -p 1401:1433 -d mcr.microsoft.com/mssql/server:2019-latest

MacOS & Linux:
docker run --name sqlserver2019 -e 'ACCEPT_EULA=Y' -e 'MSSQL_SA_PASSWORD=Aburguera1234' -p 1401:1433 -d mcr.microsoft.com/mssql/server:2019-latest


# Note that MSSQL_SA_PASSWORD and SA_PASSWORD are functionally identical. Microsoft has depreciated SA_PASSWORD but it continues to work. MSSQL_SA_PASSWORD should be used moving forward.


##################
### PostgreSQL ###

https://hub.docker.com/_/postgres/

docker run --name postgresql -p 5401:5432 -e POSTGRES_PASSWORD=Aburguera1234 -d postgres:14.8




#######################
### Docker Commands ###

# view all running containers
docker ps

# view all containers regardless of status
docker ps -a

# stop a container
docker stop sqlserver2019

# start a container
docker start sqlserver2019

# remove a container
docker rm sqlserver2019

############################
### Microsoft SQL Server ###

# Log into sqlserver2019 docker container
docker exec -it sqlserver2019 bash

# View OS information
cat /etc/os-release

# Log into SQL Server using SQLCMD command line tool
/opt/mssql-tools/bin/sqlcmd -U sa -P Aburguera1234

# View version information
SELECT @@Version;
GO

# List databases currently on the server
SELECT name FROM sys.databases;
GO

# Create a new database
CREATE DATABASE mytestdb;

# Quit SQLCMD CLI tool
exit

# Exit out of the docker container
exit




##################
### PostgreSQL ###

# Log into postgresql docker container
docker exec -it postgresql bash

# View OS information
cat /etc/os-release

# Log into PosgreSQL server using PSQL command line tool
psql -U postgres

# View help documentation
help

# List databases currently on the server
\l

# Create a new database
CREATE DATABASE mytestdb;

# Quit PSQL CLI tool
\q

# Exit out of the docker container
exit

