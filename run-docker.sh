docker build --build-arg JAR_VERSION=1.0.0 --build-arg SPRING_PROFILE=local -t cb-ubb-ingestion-service .
cd docker
docker-compose down
docker-compose build
docker-compose up