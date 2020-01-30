package com.fuelconsumption.model.repo;

/**
 * projection class to help the database function query at the repository to map the attributes
 * 
 * @author mohamed.elleithy
 *
 */

public interface FuelConsumptionGroupInterface {

	public String getFuelType();

	public String getTotalVolume();

	public String getTotalPrice();

	public String getAvgPrice();

}
