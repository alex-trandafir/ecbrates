#ECB Rates retrieval

There are two sources from which one can get the EUR rates: 
* https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml for latest rate 
* https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml for the rates in the last 90 days 

Because the services are inconsistent this projects uses an internal cache and a scheduler to retrieve the latest rates. 

#Build
```shell script
mvn clean install
```

#Startup
```shell script
java -jar target/ecbrates-0.0.1-SNAPSHOT.jar
```
  
  
#Usage
```shell script
 curl localhost:8080/rates?fromDate=2020-12-01\&toDate=2020-12-14
```
