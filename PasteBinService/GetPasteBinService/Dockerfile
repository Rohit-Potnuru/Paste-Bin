FROM eclipse-temurin:17-jdk
# Create .aws directory in the home directory of the container
RUN mkdir -p /root/.aws

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from your target directory to the working directory inside the container
COPY target/GetPasteBinService-1.0-SNAPSHOT.jar /app/GetPasteBinService-1.0-SNAPSHOT.jar

# Specify the command to run on container start
CMD ["java", "-jar", "/app/GetPasteBinService-1.0-SNAPSHOT.jar"]