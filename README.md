# A simple rest service on Spring

### Task:
The service should take 4 numbers and return the mean and median for the parameters provided.

1. Create and run locally a simple web/REST service using Spring (Spring Boot) and maven.
2. Add a GET endpoint that accepts input parameters as queryParams in the URL and returns the result as JSON according to the option.
3. Add validation of input parameters with returning a 400 error.
4. Add processing of internal unchecked errors with return of 500 error.
5. Add logging of actions and errors.
6. Write a unit test.
7. Add a simple cache in the form of an in-memory Map for the service. Map must be contained in a separate class, which must be added to the main service using Spring's dependency injection mechanism.
8. Add a service to count calls to the main service. The counter must be implemented as a separate class, access to which must be synchronized.
9. Using jmeter, configure a load test and make sure that the hit counter works correctly under heavy load.
10. Convert the original service to work with a list of parameters for bulk operations using Java 8 lambda expressions.
11. Add a POST method to call a bulk operation and transfer a list of parameters in the form of JSON.
12. Add aggregation functionality (calculating maximum, minimum, average values) for input parameters and results using Java 8 map/filters functions.
13. Add the ability to save all calculation results in a database or file using Hibernate and PostgreSQL.
14. Add the ability to asynchronously call a service using a future, return the status of a REST service call without waiting for the calculation results. The calculation results must be presented in the database using a predefined ID.
