package com.fuelconsumption.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fuelconsumption.model.entities.FuelConsumptionEntity;

/**
 * The repository class for Fuel consumption module, which contains all the database functions
 * 
 * @author mohamed.elleithy
 *
 */

@RepositoryRestResource(exported = false) // Disable export REST nature for Spring JPA
public interface FuelConsumptionRepo extends PagingAndSortingRepository<FuelConsumptionEntity, Long> {

	/*
	 * This method is required to group the fuels by fuelType and get the total volume and total price and average price
	 */
	@Query(value = "SELECT Distinct FC.FUEL_TYPE as fuelType, sum(FC.VOLUME) as totalVolume, sum(FC.PRICE) as totalPrice , (sum(FC.PRICE) / sum(FC.VOLUME)) as avgPrice FROM FUEL_CONSUMPTION FC Group By FC.FUEL_TYPE", nativeQuery = true)
	List<FuelConsumptionGroupInterface> getFuelsGroupedByFuelType();
	
}
