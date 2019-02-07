# Spring Training Workshop: School
A very simple school application developed during a hands-on workship for the Spring Training.

## Setup
After cloning the project, a Docker container for the database must be fired up or alternatively, a local PostgreSQL instance may be used (which requires adjusting the `application.yaml` file). For setting up the docker container, a docker-compose file is already part of the repository, so you must only run a `docker-compose up` command to get the database up and running.

## APIs
This application must expose REST, OData V2 and OData V4 APIs for accessing the business logic.

## Business Specification
A small application that can be used by a professor to keep track of individual grades the students from the groups that he is teaching. 

A group is described by: 
 - Study year (e.g. 1, 2, 3, etc.),
 - Group name (e.g. `30041`),
 - Specialization (e.g. `Automation`).

Each group can have multiple students, each described by:
 - Unique student number (e.g. `123-12-12`),
 - Full name (e.g. `Serban Petrescu`).

Each student may have several grades, each described by:
 - Score, integer on a scale from 1 to 10,
 - Date.

The following processes must be supported by the system:
1. At the start of the year, the teacher wants to be able to add new groups.
2. He wants to import Excels obtained from the secretary containing all students of a group
3. During the course of the year, he wants to add individual marks to students.
4. During each laboratory, he wants to select a random subset of students to check their homework.
5. After the laboratory exam, he should be able to import grades for a whole group at once.
6. At the end of the year, he should be able to get the final grade for each student:
   * Arithmetic average between all the grades of the given student.
   * If the student has less than 2 grades, he should be marked as “failed” (he did not pass the course in this semester).
