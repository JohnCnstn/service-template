# service-template
Service template for new microservices.

Architecture
PostgreSql was chosen as the database. It's is an open source object-relational database system with over 30 years of
active development that has earned it a strong reputation for reliability, feature robustness, and performance.
Openapi was chosen for designing, building, documenting and consuming REST APIs.
Ktolin was chosen for configuring Gradle.
CheckStyle, Jacoco and Pmd plugins were chosen for supporting quality and reliable code.

Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

Prerequisites
Project requires Java 11, Docker.

Installing
Instructions for Java: AdoptOpenJDK installation
Instructions for Docker: Docker installation

Running
Project requires running PostgreSql database.
docker-compose up
Then the project should be started, with the following command:
./gradlew bootRun for MacOS or Linux. gradlew bootRun for Windows.

Running the tests
./gradlew test for MacOS or Linux. gradlew test for Windows.

Contributing
When adding new feature to project make sure code satisfies rules made with CheckStyle, Pmd plugins.
Useful documentation:
For CheckStyle: CheckStyle Rules
For Pmd: Pmd Java Rules
Also minimal test coverage must be 75%. For more information visit
Jacoco Documentation
