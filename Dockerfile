# Eclipse Temurin official OpenJDK image for Java 17
FROM eclipse-temurin:17
# Copies the Java executable jar inside the container
COPY build/libs/*.jar app.jar
# Configures the container for the instructions in the ENTRYPOINT
ENTRYPOINT ["java","-jar","/app.jar"]