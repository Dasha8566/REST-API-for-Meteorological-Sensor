# REST-API-for-Meteorological-Sensor

REST API services are essential in various fields of activity, and many programmers worldwide are involved in creating REST API services of varying complexity. REST APIs enable the collection of data from any internet-connected device, such as sensors, robots, scales, conveyor systems, barcode scanners, mobile phones, and more, and process this data in some way.

In this project, we aim to receive data from a meteorological sensor and store it in a database for further analysis. To accomplish this, we need to create a REST API.

## Introduction

Imagine you've acquired a meteorological sensor capable of measuring the surrounding air temperature and detecting rain. These sensors typically look like this:
![image](https://github.com/Dasha8566/REST-API-for-Meteorological-Sensor/assets/124309306/e948abfb-50ba-4746-8b06-e39735d80d7c)


The goal is to receive data from the sensor and store it in a database, which can later be analyzed. To achieve this, we need to create a REST API service.

## REST API Overview

The REST API service will perform the following tasks:

1. Receive data from the meteorological sensor and store it in a database.
2. Provide endpoints for registering sensors and adding measurements.
3. Allow retrieval of all measurements and counting rainy days.

## REST API Specifications

The REST API should have the following endpoints:

1. **POST /sensors/registration**
   - Registers a new sensor in the system.
   - Accepts sensor data in JSON format, containing only the sensor's name.
   - Validates that a sensor with the same name doesn't already exist in the database.
   - Validates that the sensor's name is not empty and falls within the character limit (between 3 and 30 characters).

2. **POST /measurements/add**
   - Adds a new measurement received from the sensor.
   - Accepts JSON data representing temperature ("value") and rain status ("raining").
   - Validates the input data:
     - Temperature value should not be empty and should be within the range of -100 to 100.
     - Rain status should not be empty.
     - Validates the sensor's name in the input data, ensuring it matches a registered sensor in the database.
   - Records the measurement in the database, including the timestamp and associated sensor.

3. **GET /measurements**
   - Retrieves all measurements from the database.

4. **GET /measurements/rainyDaysCount**
   - Retrieves the count of rainy days from the database.

## Implementation

The project is divided into two parts:

### Part I: REST API Application

1. Create a REST API application using Spring REST, similar to the one developed in previous lessons.

2. Implement the necessary models, controllers, and services based on the provided business requirements.

3. Ensure proper validation of input data and handle potential errors effectively.

### Part II: Java Client

1. Create a Java client using RestTemplate to send 1000 requests with random temperature and rain status values to the REST API endpoint.

2. Before sending measurements, register a new sensor through the REST API.

3. Use RestTemplate to retrieve the 1000 measurements from the server by sending a GET request.

### Optional Task (Challenge):

- Plot a graph of the received temperature values using a library like xchart.
