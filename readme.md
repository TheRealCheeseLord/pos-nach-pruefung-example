
# POS Exams Seed Java

A seed project to be used for
POS examinations when
implementing using the **Java** &trade; and
**Spring** &trade; stack.


There are three parts - each meant to be solved independently.
The project definitions include all required dependencies and 
a fist test to check its validity.


## Part1 / Aufgabe 1

Is about implementing a class
model which is mapped into a relational
database.

Directory: [./Aufgabe1_ORMapping](./Aufgabe1_ORMapping)

Project definition: [Aufgabe1_ORMapping/pom.xml](./Aufgabe1_ORMapping/pom.xml)

Project root package: `ormapping`

Mind the resulting database model.

Implement tests to ensure your solution 
is correct and works.


## Aufgabe 2

Is about implementing some business
logic calculations using mostly Java
language features.

Directory: [./Aufgabe2_BusinessService](./Aufgabe2_BusinessService)

Project definition: [Aufgabe2_BusinessService/pom.xml](./Aufgabe2_BusinessService/pom.xml)

Project root package: `businessservice`

No persistence and no frontend needs to be
implemented.

Generate the required test data inside
your tests.


## Aufgabe 3

A RESTful API implementation. Showcase
your skills in implementing and testing
a RESTful API.

Directory: [./Aufgabe3_RestfulApi](./Aufgabe3_RestfulApi)

Project definition: [Aufgabe3_RestfulApi/pom.xml](./Aufgabe3_RestfulApi/pom.xml)

Project root package: `restfulapi`

No persistence implementation is expected.

Generate the required test data inside your
tests.


## _SpringBootParent

A project used as Maven parent for the other projects.

Directory: [./_SpringBootParent](./_SpringBootParent)

Project definition: [_SpringBootParent/pom.xml](./_SpringBootParent/pom.xml)

It holds all required and shared **Maven** project
definitions for the *Aufgabe* projects.

You can ignore it.


## Ensure_Lombok

Just to verify that our lombok definition works... again,
you can ignore it.

Directory: [./Ensure_Lombok](./Ensure_Lombok)

Project definition: [Ensure_Lombok/pom.xml](./Ensure_Lombok/pom.xml)


## Good luck!
