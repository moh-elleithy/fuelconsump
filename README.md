# fuelconsump
swedbank interview test fuel consumption application

Java 8 streams and lambda, Spring Boot, H2 embedded database, maven,  REST webservices, SQL query and Spring Data

API :

Add New Fuel :
http://localhost:8080/FuelConsumptions/addFuel :
Content-Type : application/json >>> Body >>> Raw >>> JSON
{
	"fuelType" : "95",
	"price" : "10.13",
	"date" : "2020-03-02",
	"driverId" : "123",
	"volume" : "25"
}

Get All Fuels :
http://localhost:8080/FuelConsumptions/getAllFuels
