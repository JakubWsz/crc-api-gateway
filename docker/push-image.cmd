@REM CALL cd ../ & mvn clean package
CALL cd ../ & docker build . --tag jakubwsz/crc-api-gateway -f docker/Dockerfile
CALL cd ../ & docker push jakubwsz/crc-api-gateway:latest