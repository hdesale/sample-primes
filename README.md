# sample-primes

_JDK Version_: Java 8

_How to run_: mvn exec:java

Description of RESTful services:

* **primes-checker**
  1. Resource path:  http://localhost:8080/api/v1/primes-checker?number={n}    
                     _Note_: 1 < n <= Integer.MAX_VALUE
  2. Example:        <http://localhost:8080/api/v1/primes-checker?number=100>
  
* **primes-in-range**
  1. Resource path:  http://localhost:8080/api/v1/primes-in-range?from={m}&to={n}    
                     _Note_: 1 < m < n <= 100000000 and n-m < 501
  2. Example:        <http://localhost:8080/api/v1/primes-in-range?from=100&to=500>

Description of important classes/design:

* **AppMain**: Starts the Grizzly http server using Jersey and Spring.

* **PrimesResourceImpl**: JAX-RS resource which serves the prime numbers related queries.

* **PrimesServiceImpl**: Service class to perform prime numbers related calculations.

* **PrimesCache**: Singleton and thread-safe cache to store max 1000 prime numbers and help improve the performance.

* **PrimesSerialCalculator**: Single threaded calculator.

* **PrimesParallelCalculator**: Multi-threaded calculator.

Performance Metrics are also captured and reported through JMX using com.codahale.metrics.MetricRegistry.
