- **Dockerfile:** *This is a script containing a series of instructions on how to build a Docker image for your application.*<br>
- **Docker Compose:** *This tool is used for defining and running multi-container Docker applications. With a docker-compose.yml 
file, you can configure your application’s services, networks, and volumes.*<br>

**./gradlew build:** *construeix el jar del projecte i el posa a build/libs. Si el tests d'integració pel build depenen de
la db com és el cas has de tenir el container amb la db corrents.*<br>

**docker start postgresql** *aixeca el container de la db.*<br>

**docker stop postgresql** *atura el container de la db.*<br>

**cd src/main/kotlin/arnau/projecte/docker:** *viatja al directori on esta el docker-compose.yml*<br>

**docker ps** *llistar containers actius*<br>

**docker-compose up --build** *aixecar docker compose (had d'estar al folder on esta el docker-compose.yml)*<br>

**Ctrl + C** *deatch del foreground de Docker que streameja logs sense tombar containers*<br>

**docker-compose up --build -d** *aixeca build però 'detached' amb el que Docker no corre en el foreground i streameja logs*<br>

**docker-compose down** *tomba els containers que ha aixecat el compose del directori on ets*<br>




