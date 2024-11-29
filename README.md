# EnergyHUB Project

### Tech used

- Java
- Spring Boot
- Spring Data
- Maven
- Neo4J

#Description

Our company is rolling out a new backend service that will allow developers to create Sensors and Gateways in our system. 
A sensor can only be connected to one Gateway at a time, while a Gateway can have N sensors connected to it.

A relationship of type CONNECTED_TO should be created between sensor and gateway when a sensor is assigned to a given gateway.

Sensors should have a type attribute. And a sensor can have multiple types assigned to it. 

Some example of sensor types could be temperature, humidity, electricity.

Try to complete the user stories below as best you can, and feel free to add in any features you'd like to see that may not be detailed here. The stories are purposefully vague and open to interpretation.

### User Stories:

- As a user I'd like to query for all the existing sensors.

- As a user I'd like to query for all gateways

- As a user I'd like to query all sensors assigned to an existing gateway

- As a user I'd like to create a new sensor

- As a user I'd like to create a new gateway

- As a user I'd like to assign a sensor to a given gateway.

- As a user I'd like to query for sensors of a certain type.

- As a user I'd like to query for a gateway that has electrical sensors assigned to it.

- Sensors can have a last_reading for each sensor type. The last_reading consists of a timestamp and a value.

- As a user I'd like to query all of the existing last_readings of a given sensor.

