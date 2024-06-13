### Microsoft SQL Server ##

https://hub.docker.com/_/microsoft-mssql-server

#### Windows PC:
docker run --name sqlserver2019 -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=Adam123456" -p 1401:1433 -d mcr.microsoft.com/mssql/server:2019-latest

#### MacOS & Linux:
docker run --name sqlserver2019 -e 'ACCEPT_EULA=Y' -e 'MSSQL_SA_PASSWORD=Aburguera1234' -p 1401:1433 -d mcr.microsoft.com/mssql/server:2019-latest


Note that MSSQL_SA_PASSWORD and SA_PASSWORD are functionally identical. Microsoft has depreciated SA_PASSWORD but it continues to work. MSSQL_SA_PASSWORD should be used moving forward.


##################
### PostgreSQL ##

https://hub.docker.com/_/postgres/

docker run --name postgresql -p 5401:5432 -e POSTGRES_PASSWORD=Aburguera1234 -d postgres:14.8




#######################
### Docker Commands 

- **docker ps**</br>*view all running containers*

- **docker ps -a**</br>*view all containers regardless of status*

- **docker stop sqlserver2019**</br>*stop a container*

- **docker start sqlserver2019**</br>*start a container*

- **docker rm sqlserver2019**</br>*remove a container*

############################
### Microsoft SQL Server ###

- **docker exec -it sqlserver2019 bash**</br>*Log into sqlserver2019 docker container*

- **cat /etc/os-release**</br>*View OS information*

- **/opt/mssql-tools/bin/sqlcmd -U sa -P Aburguera1234**</br>*Log into SQL Server using SQLCMD command line tool*

- **SELECT @@Version;</br>
GO**</br>*View version information*

- **SELECT name FROM sys.databases;</br>
GO**</br>*List databases currently on the server*

- **CREATE DATABASE mytestdb;**</br>*Create a new database*

- **exit**</br>*Quit SQLCMD CLI tool*

- **exit**</br>*Exit out of the docker container*




##################
### PostgreSQL ###

- **docker exec -it postgresql bash**</br>*Log into postgresql docker container*

- **cat /etc/os-release**</br>*View OS information*

- **psql -U postgres**</br>*Log into PosgreSQL server using PSQL command line tool*

- **help**</br>*View help documentation*

- **\l**</br>*List databases currently on the server*

- **CREATE DATABASE mytestdb;**</br>*Create a new database*

- **\q**</br>*Quit PSQL CLI tool*

- **exit**</br>*Exit out of the docker container*

