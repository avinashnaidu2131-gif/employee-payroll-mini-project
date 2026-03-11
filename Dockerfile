FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY dist/EmployeePayrollSystem.jar app.jar
CMD ["java","-jar","app.jar"]