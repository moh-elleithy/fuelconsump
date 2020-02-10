# fuelconsump
swedbank interview test fuel consumption application

Java 8 streams and lambda, Spring Boot, H2 embedded database, maven,  REST webservices, SQL query and Spring Data

API :

-Post a New Fuel :
http://localhost:8080/FuelConsumptions/addFuel
Content-Type : application/json >>> Body >>> Raw >>> JSON
{
	"fuelType" : "95",
	"price" : "10.13",
	"date" : "2020-03-02",
	"driverId" : "123",
	"volume" : "25"
}

-Get All Fuels :
http://localhost:8080/FuelConsumptions/getAllFuels

-Post a bulk of Fuels:
http://localhost:8080/FuelConsumptions/addFuelBulk
params : list of JSON objects which Spring REST will maps to FuelConsumptionEntity objects

-Put (update) an excist fuel :
http://localhost:8080/FuelConsumptions/updateFuel
param : JSON object of the fuel

- delete a fuel :
http://localhost:8080/FuelConsumptions/deleteFuel/{id}
param : id = id of wanted to delete fuel

- Calculating the total money spent per month for one driver or for all drivers if no driver ID received
http://localhost:8080/FuelConsumptions/getTotalMoneySpentPerMonth{monthId , driverId}

- Retrieving all the fuels records for a specific month and for one driver or for all drivers if no driver ID received
http://localhost:8080/FuelConsumptions/getAllFuelsPerMonth{monthId , driverId}

- Grouping all the Fuels consumption records in the database by fuel type and calculate the "total volume", "average price" and "total price"
http://localhost:8080/FuelConsumptions//getAllFuelsGroupedByType




