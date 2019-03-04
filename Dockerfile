FROM openjdk:8
ADD target/engineer-exam.jar engineer-exam.jar
ENTRYPOINT ["java", "-jar", "engineer-exam.jar"]
