docker build --build-arg JAR_VERSION=1.0.0 -t cb-ubb-ingestion-service .
docker run -p 8080:8080 cb-ubb-ingestion-service